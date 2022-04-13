<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/13
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="GBK" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>模型注册</title>
    <!-- base标签固定相对路径跳转的结果，book对应到web那个目录，都从web开始-->
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery.min.js"></script>
    <script type="text/javascript" src="static/script/scrollable.js"></script>
    <script type="text/javascript">
        $(function () {
            /*存储开发者上传的jar包解析后的实现类名、类中所有的方法信息以及方法所有的参数信息*/
            var className = null;
            var methods = null;
            var params = null;
            var filePath = null;

            // 根据模型大类的选择动态生成模型目录类
            $("#modelType").change(function () {
                let modelType = $(this).val();
                // alert(modelType);
                $.ajax({
                    url: "${basePath}modelServlet?action=modelDirectory",
                    type: "POST",
                    data: {
                        "type": "ajax",
                        "modelType": modelType
                    },
                    dataType: "json",
                    success: function (data) {
                        $(".modelDirectoryItem").remove();
                        for (let key in data) {
                            // alert(data);
                            if (data[key].length === 0) {
                                // 没有子分类
                                $("#modelDirectoryDown").before(
                                    "<option class='modelDirectoryItem' value=" + key + ">" + key
                                )
                            } else {
                                // 有子分类
                                let str = "<optgroup data-icon='fa fa-user' class='modelDirectoryItem' label=" + key + ">";
                                for (let i = 0; i < data[key].length; i++) {
                                    str += "<option class='modelDirectoryItem' value=" + data[key][i] + ">" + data[key][i];
                                }
                                str += "</optgroup>";
                                $("#modelDirectoryDown").before(str);
                            }
                        }
                    },
                })
            })
            // 方法测试
            $(document).on("click", ".testMethod", function () {
                // alert("事件响应");
                // 获得文件路径、方法类名、方法调用名，参数示例和参数类型
                let methodCallName = $(this).parents(".methodInfo").find("input[name='methodCallName']").val();
                let paramSamples = [];
                let paramTypes = [];
                $("input[name='paramSample'][method=" + methodCallName + "]").each(function () {
                    paramSamples.push($(this).val());
                })
                $("input[name='paramType'][method=" + methodCallName + "]").each(function () {
                    paramTypes.push($(this).val());
                })
                $.ajax({
                    type: "post",
                    url: "${basePath}modelServlet?action=testMethod",
                    data: {
                        "methodCallName": methodCallName,
                        "className": className,
                        "paramSamples": paramSamples,
                        "paramTypes": paramTypes,
                        "filePath": filePath
                    },
                    traditional: true,
                    dataType: "json",
                    success: function (data) {
                        // 将返回值送到实际返回值里面
                        // alert("data:" + data.result);
                        $("textarea[name='realResult'][method=" + methodCallName + "]").val(data.result);
                    }
                })
            })
            /*实现点击上一步、下一步，窗口滑到最上面*/
            $('.next,.prev').click(function () {
                //将当前窗口的内容区滚动高度改为0，即顶部
                $("html,body").animate({scrollTop: 0}, "fast");
            });
            /*上传jar包后 解析Jar包文件，返回方法、参数、类信息*/
            $("#modelFile").change(function () {
                // jax请求发给服务器解析
                let files = $(this).prop("files");
                let fileName = $(this).val().split("\\");
                let formData = new FormData;
                formData.append(fileName[fileName.length - 1], files[0])
                // alert("文件名" + fileName[fileName.length - 1]);
                $.ajax({
                    url: "${basePath}modelServlet?action=parseJar",
                    type: "POST",
                    data: formData,
                    contentType: false,
                    processData: false,
                    dataType: "json",
                    success: function (data) {
                        className = data['className'];
                        methods = data['methods'];
                        params = data['params'];
                        filePath = data['filePath'];
                        // 设置 implementationClass input的值，这个值在表单提交时仍存在
                        $("#implementationClass").val(className);
                        $("#methodTable").remove();
                        let str = "<table id='methodTable' border='1' '> <tr> <td> 方法调用名称 <td>返回值类型 <td> 参数个数 </tr>";
                        // 生成方法表，让开发者选择要注册的方法
                        for (let key in methods) {
                            str += "<tr> <td> <input type='checkbox' name='selectMethod' value=" + key +
                                ">" + key + "<td>" + methods[key].returnType + "<td>" + methods[key].parameterCount + "</tr>";
                        }
                        str += "</table>";
                        $("#methodsPackageInformation").after(str);
                    },
                })
            })
            /*模型注册分页的事件绑定*/
            $("#wizard").scrollable({
                //onSeek：function();当滚动当前page后发生的事情
                onSeek: function (event, i) {
                    $("#status li").removeClass("active").eq(i).addClass("active");
                },
                //onBeforeSeek：function();滚动之前发生的事情，即验证表单、绑定事件等操作
                onBeforeSeek: function (event, i) {
                    if (i === 1) {
                        if ($("#modelName").val() === "") {
                            alert("请输入模型名称");
                            return false;
                        }
                        let modelName = $("#modelName").val();
                        let result = false;
                        $.ajax({
                            url: "${basePath}modelServlet?action=existModelName",
                            type: "POST",
                            data: {
                                "modelName": modelName
                            },
                            async: false,
                            dataType: "text",
                            success: function (data) {
                                if (data === "true") {
                                    result = true;
                                    alert("已存在名为[" + modelName + "]的模型，请重新输入模型名称");
                                }
                            },
                        })
                        if (result === true) {
                            return false;
                        }
                        if ($("#modelType").val() === "请选择") {
                            alert("请选择模型类型");
                            return false;
                        }
                        if ($("#developmentLanguage").val() === "") {
                            alert("请输入开发语言");
                            return false;
                        }
                        if ($("#version").val() === "") {
                            alert("请输入版本号");
                            return false;
                        }
                        if ($("#modelDescription").val() === "") {
                            alert("请输入模型简介");
                            return false;
                        }
                        if ($("#createDepartment").val() === "") {
                            alert("请输入创建单位");
                            return false;
                        }
                        if ($("#completionDepartment").val() === "") {
                            alert("请输入完成单位");
                            return false;
                        }
                        if ($("#completionDate").val() === "") {
                            alert("请输入完成日期");
                            return false;
                        }
                        if ($("#modelDirectoryCode").val() === "请选择") {
                            alert("请选择模型归类");
                            return false;
                        }
                        if ($("#runtimeEnvironment").val() === "") {
                            alert("请输入运行环境介绍");
                            return false;
                        }
                        if ($("#testDescription").val() === "") {
                            alert("请输入测试说明");
                            return false;
                        }

                    }
                    // 获得开发者选择的方法， 在下一页生成方法信息和参数信息
                    if (i === 2) {
                        if ($("#modelFile").val() === "") {
                            alert("请先上传jar文件");
                            return false;
                        }
                        $(".methodInfo").remove();
                        let methodIndex = 1;
                        // 插入每个已选择方法的方法信息
                        $("input[name='selectMethod']:checked").each(function () {
                            let methodName = $(this).val();
                            let methodStr = "<div class='methodInfo'><div>方法" + methodIndex + "</div><fieldset><legend>方法基本信息</legend>" +
                                "<table>" +
                                "<tr>" +
                                "<td>返回值类型：<td><input type='text' name='returnType' readonly='readonly' value=" + methods[methodName].returnType + ">" +
                                "<td>方法类名<td><input type='text' name='className' readonly='readonly' value=" + className + ">" +
                                "</tr>" +
                                "<tr>" +
                                "<td>调用名称：<td><input type='text' readonly='readonly' name='methodCallName' value=" + methodName + ">" +
                                "<td>方法名称<td><input type='text' name='methodName' value=" + methodName + ">" +
                                "</tr>" +
                                "<tr>" +
                                "<td>功能描述：<td colspan='3'> <textarea rows='8' cols='75' name='methodDescription'></textarea>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>参数个数<td><input type='text' name='parameterCount'  readonly='readonly' value=" + methods[methodName].parameterCount + ">" +
                                "</tr>" +
                                "</table>" + "</fieldset>" +
                                "<fieldset><legend>方法参数信息</legend>" +
                                "<table class='paramTable' >" +
                                "<tr>" +
                                "<td>序号<td>参数名称<td>参数类型<td>参数示例<td>参数描述" +
                                "</tr>" +
                                "</table>" + "</fieldset>" +
                                "<fieldset><legend>方法测试</legend>" +
                                "<table>" +
                                "<tr>" +
                                "<td>预期结果<td colspan='3'><textarea rows='8' cols='80' name='expectedResult'></textarea>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>实际结果<td colspan='3'><textarea rows='8' cols='80' name='realResult' readonly='readonly' method=" + methodName + "></textarea>" +
                                "</tr>" +
                                "<tr style='overflow: hidden'><td colspan='4' ><input type='button' style='float:right;margin-right: 50px' class='testMethod'  value='测试'></tr>" +
                                "</table>" + "</fieldset>" + "</div>";
                            $("#methodInfoBottom").before(methodStr);
                            methodIndex++;
                            // 插入对应方法的参数
                            for (let i = 0; i < methods[methodName].parameterCount; i++) {
                                $(".paramTable:last tr:last").after(
                                    "<tr>" +
                                    "<td>" + (i + 1) + "<td><input type='text'  name='paramName'>" + "<td><input type='text' readonly='readonly' value=" + params[methodName][i] + " name='paramType' style='width: 100px;' method=" + methodName + ">" + "<td><input type='text' name='paramSample' method=" + methodName + "><td><input type='text' name='paramDescription'>" +
                                    "</tr>"
                                )
                            }

                        })

                    }
                }
            });
        });
    </script>
    <link rel="stylesheet" type="text/css" href="static/css/main.css"/>
    <style>
        #wizard {
            border: 5px solid #789;
            font-size: 12px;
            /*height: 900px;*/
            margin: 20px auto;
            /* 根据每个页面宽度设置整个框框宽度（只显示一个页面） */
            width: 850px;
            overflow: hidden;
            position: relative;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
        }

        ::-webkit-scrollbar {
            display: none;
        }

        #wizard .items {
            /* 比 page width 大就行 */
            width: 10000px;
            clear: both;
            overflow: hidden;
            position: relative;
        }

        #wizard .right {
            float: right;
        }

        #wizard #status {
            height: 35px;
            background: #123;
            padding-left: 25px !important;
        }

        #status li {
            float: left;
            color: #fff;
            padding: 10px 30px;
        }

        #status li.active {
            background-color: #369;
            font-weight: normal;
        }

        .page {
            padding: 20px 30px;
            /* 设置每个页面宽度 */
            width: 800px;
            height: auto;
            float: left;
        }

        .page h3 {
            height: 42px;
            font-size: 16px;
            border-bottom: 1px dotted #ccc;
            margin-bottom: 20px;
            padding-bottom: 5px
        }

        .page h3 em {
            font-size: 12px;
            font-weight: 500;
            font-style: normal
        }

        .page p {
            line-height: 24px;
        }

        .page p label {
            font-size: 14px;
            display: block;
        }

        table {
            width: 700px;
            margin: 0 auto;
        }

        .paramTable {
            text-align: center;
        }

        table tr {
            height: 30px;
        }

        .btn_nav {
            height: 36px;
            display: flex;
            justify-content: center;
            /* line-height: 36px; */
            margin: 20px auto;
        }

        textarea {
            resize: none;
        }

        .prev,
        .next {
            width: 100px;
            height: 32px;
            margin: 0 auto;
            line-height: 32px;
            /*background: url(btn_bg.gif) repeat-x bottom;*/
            border: 1px solid #d3d3d3;
            cursor: pointer
        }

        fieldset {
            border-color: rgba(30, 30, 30, 0.2);
        }
    </style>
</head>
<body>

<div id="main">
    <h2 class="top_title">模型注册引导</h2>
    <%--    <p style="height:24px; line-height:24px; margin:16px"></p>--%>
    <form action="${basePath}modelServlet?action=register" method="post" enctype="multipart/form-data">
        <div id="wizard">
            <ul id="status">
                <li class="active"><strong>1.</strong>模型基本信息</li>
                <li><strong>2.</strong>方法包</li>
                <li><strong>3.</strong>方法信息与测试</li>
                <li><strong>4.</strong>模型附件</li>
                <li><strong>5.</strong>注册</li>
            </ul>
            <div class="items">
                <div class="page">
                    <h3>模型基本信息<br/><em>请确保模型信息真实有效</em></h3>
                    <table>
                        <tr>
                            <td class="td_width">模型名称</td>
                            <td>
                                <input type="text" name="modelName" size="30" id="modelName"/>
                            </td>
                            <td class="td_width">模型类型</td>
                            <td>
                                <select name="modelType" id="modelType">
                                    <option value="请选择">请选择</option>
                                    <option value="计算模型">计算模型</option>
                                    <option value="功能模型">功能模型</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                开发语言
                            </td>
                            <td>
                                <select name="developmentLanguage" id="developmentLanguage">
                                    <option value="java">java</option>
                                    <option value="c++">c++</option>
                                    <option value="python">python</option>
                                    <option value="php">php</option>
                                </select>
                            </td>
                            <td>
                                版本号
                            </td>
                            <td>
                                <input type="text" name="version" id="version">
                            </td>
                        </tr>
                        <tr>
                            <td>模型简介</td>
                            <td colspan="3"><textarea rows="8" cols="80" name="modelDescription"></textarea></td>
                        </tr>
                        <tr>
                            <td>
                                应用领域
                            </td>
                            <td colspan="3">
                                <input type="checkbox" name="applicationField" value="海军领域" checked="checked">海军领域
                                <%--                                <input type="checkbox" name="applicationField" value="陆军领域">陆军领域--%>
                                <%--                                <input type="checkbox" name="applicationField" value="空军领域">空军领域--%>
                                <%--                                <input type="checkbox" name="applicationField" value="其他">其他--%>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                模型标签
                            </td>
                            <td colspan="3">
                                <input type="checkbox" name="modelTags" value="数据">数据
                                <input type="checkbox" name="modelTags" value="算法">算法
                                <input type="checkbox" name="modelTags" value="统计">统计
                                <input type="checkbox" name="modelTags" value="分析">分析

                            </td>
                        </tr>
                        <tr>
                            <td>
                                创建单位
                            </td>
                            <td>
                                <input type="text" name="createDepartment" id="createDepartment">
                            </td>
                            <td>
                                完成单位
                            </td>
                            <td>
                                <input type="text" name="completionDepartment" id="completionDepartment">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                完成日期
                            </td>
                            <td>
                                <input type="date" name="completionDate" id="completionDate">
                            </td>
                            <td>
                                模型归类
                            </td>
                            <td>
                                <select name="modelDirectoryCode" id="modelDirectoryCode">
                                    <option> 请选择</option>
                                    <%--                                    <option value="数据采报模型">数据采报模型</option>--%>
                                    <%--                                    <option value="数据产品生成模型">数据产品生成模型</option>--%>
                                    <%--                                    <option value="数据管理维护模型">数据管理维护模型</option>--%>
                                    <%--                                    <option value="数据可视化模型">数据可视化模型</option>--%>
                                    <%--                                    <option value="数据审核模型">数据审核模型</option>--%>
                                    <option hidden id="modelDirectoryDown"></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>运行环境</td>
                            <td colspan="3"><textarea rows="8" cols="80" name="runtimeEnvironment"
                                                      id="runtimeEnvironment"></textarea></td>
                        </tr>
                        <tr>
                            <td>测试说明</td>
                            <td colspan="3"><textarea rows="8" cols="80" name="testDescription"
                                                      id="testDescription"></textarea></td>
                        </tr>
                    </table>
                    <div class="btn_nav">
                        <input type="button" class="next" value="下一步&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>填写方法包信息<br/><em>请确保方法包信息真实有效！</em></h3>
                    <table id="methodsPackageInformation">
                        <tr>
                            <td style="text-align: center">
                                方法包（Jar文件）
                            </td>
                            <td>
                                <input type="file" name="modelFile" id="modelFile"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_width" style="text-align: center">实现类:</td>
                            <td colspan="3">
                                <!-- 这个jar包的实现类 只支持一个 -->
                                <input type="text" name="implementationClass" readonly="readonly"
                                       id="implementationClass">
                            </td>
                        <tr>
                            <!-- 根据用户选择的类，把方法给列出来 表格形式的复选框-->
                    </table>
                    <div class="btn_nav">
                        <input type="button" class="prev" value="&laquo;上一步"/>
                        <!-- 点击下一步时，获取选择的方法，在下一页中列出参数信息 -->
                        <input type="button" class="next" value="下一步&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>填写方法信息<br/><em>请确保各方法信息真实有效！</em></h3>
                    <div class="btn_nav" id="methodInfoBottom">
                        <input type="button" class="prev" value="&laquo;上一步"/>
                        <input type="button" class="next " value="下一步&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>模型附件上传<br/><em>请上传模型相关的附件并填写相关信息！</em></h3>
                    <table>
                        <tr>
                            <td class="td_width">附件上传：</td>
                            <td><input type="file" name="attachment"></td>
                        </tr>
                        <tr>
                            <td>附件描述:</td>
                            <td><textarea rows="4" cols="80" name="attachmentDescription"></textarea></td>
                        </tr>
                    </table>
                    <div class="btn_nav">
                        <input type="button" class="prev" style="float:left" value="&laquo;上一步"/>
                        <input type="button" class="next right" value="下一步&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>完成注册<br/><em>点击确定提交注册申请，待管理员审核后进行发布。</em></h3>
                    <br/>
                    <div class="btn_nav">
                        <input type="button" class="prev" style="float:left" value="&laquo;上一步"/>
                        <input type="submit" class="next" id="sub" value="确定"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <br/>
</div>
</body>
</html>
