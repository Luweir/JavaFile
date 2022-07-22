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
    <title>ģ��ע��</title>
    <!-- base��ǩ�̶����·����ת�Ľ����book��Ӧ��web�Ǹ�Ŀ¼������web��ʼ-->
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery.min.js"></script>
    <script type="text/javascript" src="static/script/scrollable.js"></script>
    <script type="text/javascript">
        $(function () {
            /*�洢�������ϴ���jar���������ʵ���������������еķ�����Ϣ�Լ��������еĲ�����Ϣ*/
            var className = null;
            var methods = null;
            var params = null;
            var filePath = null;

            // ����ģ�ʹ����ѡ��̬����ģ��Ŀ¼��
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
                                // û���ӷ���
                                $("#modelDirectoryDown").before(
                                    "<option class='modelDirectoryItem' value=" + key + ">" + key
                                )
                            } else {
                                // ���ӷ���
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
            // ��������
            $(document).on("click", ".testMethod", function () {
                // alert("�¼���Ӧ");
                // ����ļ�·������������������������������ʾ���Ͳ�������
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
                        // ������ֵ�͵�ʵ�ʷ���ֵ����
                        // alert("data:" + data.result);
                        $("textarea[name='realResult'][method=" + methodCallName + "]").val(data.result);
                    }
                })
            })
            /*ʵ�ֵ����һ������һ�������ڻ���������*/
            $('.next,.prev').click(function () {
                //����ǰ���ڵ������������߶ȸ�Ϊ0��������
                $("html,body").animate({scrollTop: 0}, "fast");
            });
            /*�ϴ�jar���� ����Jar���ļ������ط���������������Ϣ*/
            $("#modelFile").change(function () {
                // jax���󷢸�����������
                let files = $(this).prop("files");
                let fileName = $(this).val().split("\\");
                let formData = new FormData;
                formData.append(fileName[fileName.length - 1], files[0])
                // alert("�ļ���" + fileName[fileName.length - 1]);
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
                        // ���� implementationClass input��ֵ�����ֵ�ڱ��ύʱ�Դ���
                        $("#implementationClass").val(className);
                        $("#methodTable").remove();
                        let str = "<table id='methodTable' border='1' '> <tr> <td> ������������ <td>����ֵ���� <td> �������� </tr>";
                        // ���ɷ������ÿ�����ѡ��Ҫע��ķ���
                        for (let key in methods) {
                            str += "<tr> <td> <input type='checkbox' name='selectMethod' value=" + key +
                                ">" + key + "<td>" + methods[key].returnType + "<td>" + methods[key].parameterCount + "</tr>";
                        }
                        str += "</table>";
                        $("#methodsPackageInformation").after(str);
                    },
                })
            })
            /*ģ��ע���ҳ���¼���*/
            $("#wizard").scrollable({
                //onSeek��function();��������ǰpage����������
                onSeek: function (event, i) {
                    $("#status li").removeClass("active").eq(i).addClass("active");
                },
                //onBeforeSeek��function();����֮ǰ���������飬����֤�������¼��Ȳ���
                onBeforeSeek: function (event, i) {
                    if (i === 1) {
                        if ($("#modelName").val() === "") {
                            alert("������ģ������");
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
                                    alert("�Ѵ�����Ϊ[" + modelName + "]��ģ�ͣ�����������ģ������");
                                }
                            },
                        })
                        if (result === true) {
                            return false;
                        }
                        if ($("#modelType").val() === "��ѡ��") {
                            alert("��ѡ��ģ������");
                            return false;
                        }
                        if ($("#developmentLanguage").val() === "") {
                            alert("�����뿪������");
                            return false;
                        }
                        if ($("#version").val() === "") {
                            alert("������汾��");
                            return false;
                        }
                        if ($("#modelDescription").val() === "") {
                            alert("������ģ�ͼ��");
                            return false;
                        }
                        if ($("#createDepartment").val() === "") {
                            alert("�����봴����λ");
                            return false;
                        }
                        if ($("#completionDepartment").val() === "") {
                            alert("��������ɵ�λ");
                            return false;
                        }
                        if ($("#completionDate").val() === "") {
                            alert("�������������");
                            return false;
                        }
                        if ($("#modelDirectoryCode").val() === "��ѡ��") {
                            alert("��ѡ��ģ�͹���");
                            return false;
                        }
                        if ($("#runtimeEnvironment").val() === "") {
                            alert("���������л�������");
                            return false;
                        }
                        if ($("#testDescription").val() === "") {
                            alert("���������˵��");
                            return false;
                        }

                    }
                    // ��ÿ�����ѡ��ķ����� ����һҳ���ɷ�����Ϣ�Ͳ�����Ϣ
                    if (i === 2) {
                        if ($("#modelFile").val() === "") {
                            alert("�����ϴ�jar�ļ�");
                            return false;
                        }
                        $(".methodInfo").remove();
                        let methodIndex = 1;
                        // ����ÿ����ѡ�񷽷��ķ�����Ϣ
                        $("input[name='selectMethod']:checked").each(function () {
                            let methodName = $(this).val();
                            let methodStr = "<div class='methodInfo'><div>����" + methodIndex + "</div><fieldset><legend>����������Ϣ</legend>" +
                                "<table>" +
                                "<tr>" +
                                "<td>����ֵ���ͣ�<td><input type='text' name='returnType' readonly='readonly' value=" + methods[methodName].returnType + ">" +
                                "<td>��������<td><input type='text' name='className' readonly='readonly' value=" + className + ">" +
                                "</tr>" +
                                "<tr>" +
                                "<td>�������ƣ�<td><input type='text' readonly='readonly' name='methodCallName' value=" + methodName + ">" +
                                "<td>��������<td><input type='text' name='methodName' value=" + methodName + ">" +
                                "</tr>" +
                                "<tr>" +
                                "<td>����������<td colspan='3'> <textarea rows='8' cols='75' name='methodDescription'></textarea>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>��������<td><input type='text' name='parameterCount'  readonly='readonly' value=" + methods[methodName].parameterCount + ">" +
                                "</tr>" +
                                "</table>" + "</fieldset>" +
                                "<fieldset><legend>����������Ϣ</legend>" +
                                "<table class='paramTable' >" +
                                "<tr>" +
                                "<td>���<td>��������<td>��������<td>����ʾ��<td>��������" +
                                "</tr>" +
                                "</table>" + "</fieldset>" +
                                "<fieldset><legend>��������</legend>" +
                                "<table>" +
                                "<tr>" +
                                "<td>Ԥ�ڽ��<td colspan='3'><textarea rows='8' cols='80' name='expectedResult'></textarea>" +
                                "</tr>" +
                                "<tr>" +
                                "<td>ʵ�ʽ��<td colspan='3'><textarea rows='8' cols='80' name='realResult' readonly='readonly' method=" + methodName + "></textarea>" +
                                "</tr>" +
                                "<tr style='overflow: hidden'><td colspan='4' ><input type='button' style='float:right;margin-right: 50px' class='testMethod'  value='����'></tr>" +
                                "</table>" + "</fieldset>" + "</div>";
                            $("#methodInfoBottom").before(methodStr);
                            methodIndex++;
                            // �����Ӧ�����Ĳ���
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
            /* ����ÿ��ҳ����������������ȣ�ֻ��ʾһ��ҳ�棩 */
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
            /* �� page width ����� */
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
            /* ����ÿ��ҳ���� */
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
    <h2 class="top_title">ģ��ע������</h2>
    <%--    <p style="height:24px; line-height:24px; margin:16px"></p>--%>
    <form action="${basePath}modelServlet?action=register" method="post" enctype="multipart/form-data">
        <div id="wizard">
            <ul id="status">
                <li class="active"><strong>1.</strong>ģ�ͻ�����Ϣ</li>
                <li><strong>2.</strong>������</li>
                <li><strong>3.</strong>������Ϣ�����</li>
                <li><strong>4.</strong>ģ�͸���</li>
                <li><strong>5.</strong>ע��</li>
            </ul>
            <div class="items">
                <div class="page">
                    <h3>ģ�ͻ�����Ϣ<br/><em>��ȷ��ģ����Ϣ��ʵ��Ч</em></h3>
                    <table>
                        <tr>
                            <td class="td_width">ģ������</td>
                            <td>
                                <input type="text" name="modelName" size="30" id="modelName"/>
                            </td>
                            <td class="td_width">ģ������</td>
                            <td>
                                <select name="modelType" id="modelType">
                                    <option value="��ѡ��">��ѡ��</option>
                                    <option value="����ģ��">����ģ��</option>
                                    <option value="����ģ��">����ģ��</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                ��������
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
                                �汾��
                            </td>
                            <td>
                                <input type="text" name="version" id="version">
                            </td>
                        </tr>
                        <tr>
                            <td>ģ�ͼ��</td>
                            <td colspan="3"><textarea rows="8" cols="80" name="modelDescription"></textarea></td>
                        </tr>
                        <tr>
                            <td>
                                Ӧ������
                            </td>
                            <td colspan="3">
                                <input type="checkbox" name="applicationField" value="��������" checked="checked">��������
                                <%--                                <input type="checkbox" name="applicationField" value="½������">½������--%>
                                <%--                                <input type="checkbox" name="applicationField" value="�վ�����">�վ�����--%>
                                <%--                                <input type="checkbox" name="applicationField" value="����">����--%>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                ģ�ͱ�ǩ
                            </td>
                            <td colspan="3">
                                <input type="checkbox" name="modelTags" value="����">����
                                <input type="checkbox" name="modelTags" value="�㷨">�㷨
                                <input type="checkbox" name="modelTags" value="ͳ��">ͳ��
                                <input type="checkbox" name="modelTags" value="����">����

                            </td>
                        </tr>
                        <tr>
                            <td>
                                ������λ
                            </td>
                            <td>
                                <input type="text" name="createDepartment" id="createDepartment">
                            </td>
                            <td>
                                ��ɵ�λ
                            </td>
                            <td>
                                <input type="text" name="completionDepartment" id="completionDepartment">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                �������
                            </td>
                            <td>
                                <input type="date" name="completionDate" id="completionDate">
                            </td>
                            <td>
                                ģ�͹���
                            </td>
                            <td>
                                <select name="modelDirectoryCode" id="modelDirectoryCode">
                                    <option> ��ѡ��</option>
                                    <%--                                    <option value="���ݲɱ�ģ��">���ݲɱ�ģ��</option>--%>
                                    <%--                                    <option value="���ݲ�Ʒ����ģ��">���ݲ�Ʒ����ģ��</option>--%>
                                    <%--                                    <option value="���ݹ���ά��ģ��">���ݹ���ά��ģ��</option>--%>
                                    <%--                                    <option value="���ݿ��ӻ�ģ��">���ݿ��ӻ�ģ��</option>--%>
                                    <%--                                    <option value="�������ģ��">�������ģ��</option>--%>
                                    <option hidden id="modelDirectoryDown"></option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>���л���</td>
                            <td colspan="3"><textarea rows="8" cols="80" name="runtimeEnvironment"
                                                      id="runtimeEnvironment"></textarea></td>
                        </tr>
                        <tr>
                            <td>����˵��</td>
                            <td colspan="3"><textarea rows="8" cols="80" name="testDescription"
                                                      id="testDescription"></textarea></td>
                        </tr>
                    </table>
                    <div class="btn_nav">
                        <input type="button" class="next" value="��һ��&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>��д��������Ϣ<br/><em>��ȷ����������Ϣ��ʵ��Ч��</em></h3>
                    <table id="methodsPackageInformation">
                        <tr>
                            <td style="text-align: center">
                                ��������Jar�ļ���
                            </td>
                            <td>
                                <input type="file" name="modelFile" id="modelFile"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_width" style="text-align: center">ʵ����:</td>
                            <td colspan="3">
                                <!-- ���jar����ʵ���� ֻ֧��һ�� -->
                                <input type="text" name="implementationClass" readonly="readonly"
                                       id="implementationClass">
                            </td>
                        <tr>
                            <!-- �����û�ѡ����࣬�ѷ������г��� �����ʽ�ĸ�ѡ��-->
                    </table>
                    <div class="btn_nav">
                        <input type="button" class="prev" value="&laquo;��һ��"/>
                        <!-- �����һ��ʱ����ȡѡ��ķ���������һҳ���г�������Ϣ -->
                        <input type="button" class="next" value="��һ��&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>��д������Ϣ<br/><em>��ȷ����������Ϣ��ʵ��Ч��</em></h3>
                    <div class="btn_nav" id="methodInfoBottom">
                        <input type="button" class="prev" value="&laquo;��һ��"/>
                        <input type="button" class="next " value="��һ��&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>ģ�͸����ϴ�<br/><em>���ϴ�ģ����صĸ�������д�����Ϣ��</em></h3>
                    <table>
                        <tr>
                            <td class="td_width">�����ϴ���</td>
                            <td><input type="file" name="attachment"></td>
                        </tr>
                        <tr>
                            <td>��������:</td>
                            <td><textarea rows="4" cols="80" name="attachmentDescription"></textarea></td>
                        </tr>
                    </table>
                    <div class="btn_nav">
                        <input type="button" class="prev" style="float:left" value="&laquo;��һ��"/>
                        <input type="button" class="next right" value="��һ��&raquo;"/>
                    </div>
                </div>
                <div class="page">
                    <h3>���ע��<br/><em>���ȷ���ύע�����룬������Ա��˺���з�����</em></h3>
                    <br/>
                    <div class="btn_nav">
                        <input type="button" class="prev" style="float:left" value="&laquo;��һ��"/>
                        <input type="submit" class="next" id="sub" value="ȷ��"/>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <br/>
</div>
</body>
</html>
