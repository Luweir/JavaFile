<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/12/2
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>Title</title>
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            $("body").on("click", ".addDirectory", function () {
                let directoryName = prompt("请输入新增的目录名：");
                let parentName = $(this).parent().attr("belongDirectory");
                // alert(parentName);
                $.ajax({
                    url: "${basePath}modelServlet?action=updateDirectory",
                    type: "POST",
                    data: {
                        "type": "add",
                        "directoryName": directoryName,
                        "parentName": parentName
                    },
                    dataType: "text",
                    success: function (data) {
                        if (data === "true") {
                            alert("添加目录成功");
                        } else {
                            alert("添加目录失败");
                        }
                    },
                })
                $(this).before(
                    "<li haveChild='false'> <label>" + directoryName + "</label>" +
                    "<input type='button' class='rename' value='修改'>" +
                    "<input type='button' class='drop' value='删除'>" +
                    "</li>"
                )
            })
            $("body").on("click", ".drop", function () {
                let directoryName = $(this).parent().find("label").text();
                $.ajax({
                    url: "${basePath}modelServlet?action=updateDirectory",
                    type: "POST",
                    data: {
                        "type": "drop",
                        "directoryName": directoryName,
                    },
                    dataType: "text",
                    success: function (data) {
                        if (data === "true") {
                            alert("删除成功");
                        } else {
                            alert("删除失败");
                        }
                    },
                })
                $(this).parent().remove();
            })
            $("body").on("click", ".rename", function () {
                let oldName = $(this).parent().find("label").text();
                let newName = prompt("将[" + oldName + "]标签重命名为：");
                // alert(newName);
                $.ajax({
                    url: "${basePath}modelServlet?action=updateDirectory",
                    type: "POST",
                    data: {
                        "type": "rename",
                        "oldName": oldName,
                        "newName": newName
                    },
                    dataType: "text",
                    success: function (data) {
                        if (data === "true") {
                            alert("目录命名成功");
                        } else {
                            alert("重命名失败");
                        }
                    },
                })
                $(this).parent().find("label").text(newName);
            })
            $("#firstLayer li").click(function () {
                $(this).parent().children().css("background-color", "#ffffff");
                $(this).css("background-color", "#a6e1db");
                let directoryName = $(this).find("label").text();
                $("#secondLayer").attr("belongDirectory", directoryName)
                // alert(directoryName);
                $.ajax({
                    url: "${basePath}modelServlet?action=classificationManagement",
                    type: "POST",
                    data: {
                        "directoryName": directoryName
                    },
                    dataType: "json",
                    success: function (data) {
                        $("#secondLayer").children().remove();

                        $("#thirdLayer").children().remove();
                        for (let i = 0; i < data.length; i++) {
                            $("#secondLayer").append(
                                "<li haveChild=" + data[i].haveChild + "> <label>" + data[i].curDirectory + "</label>" +
                                "<input type='button' class='rename' value='修改'>" +
                                "<input type='button' class='drop' value='删除'>" +
                                "</li>"
                            )
                        }
                        $("#secondLayer").append("<input type='button' class='addDirectory' style='margin-top: 20px' value='新增分类'>")
                    },
                })
            })
            $("#secondLayer").on("click", "li", function () {
                $(this).parent().children().css("background-color", "#ffffff")
                $(this).css("background-color", "#a6e1db")
                let directoryName = $(this).find("label").text();
                $("#thirdLayer").attr("belongDirectory", directoryName);
                // alert(directoryName);
                $.ajax({
                    url: "${basePath}modelServlet?action=classificationManagement",
                    type: "POST",
                    data: {
                        "directoryName": directoryName
                    },
                    dataType: "json",
                    success: function (data) {
                        $("#thirdLayer").children().remove();
                        for (let i = 0; i < data.length; i++) {
                            $("#thirdLayer").append(
                                "<li haveChild=" + data[i].haveChild + "> <label>" + data[i].curDirectory + "</label>" +
                                "<input type='button' class='rename' value='修改'>" +
                                "<input type='button' class='drop' value='删除'>" +
                                "</li>"
                            )
                        }
                        $("#thirdLayer").append("<input type='button' class='addDirectory' style='margin-top: 20px' value='新增分类'>")
                    },
                })
            })
        })

    </script>
    <style>
        #main {
            width: 1200px;
            margin: 20px auto;
            overflow: hidden;

        }

        .classify {
            text-align: center;
            float: left;
        }

        .classify div {
            width: 300px;
            margin: 20px 20px 0px 20px;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            color: #2b2b2b;
        }

        li {
            height: 40px;
            line-height: 40px;
            margin: 10px 0 0 0;
            padding: 0;
            border: #1e1e1e 1px solid;
        }

        label {
            display: inline-block;
            width: 200px;
        }

        table {
            width: 600px;
        }

        table tr {
            text-align: center;
        }
    </style>


</head>
<body>
<div id="main">
    <div class="classify">
        <p>一级分类</p>
        <div>
            <ul id="firstLayer">
                <li id="calculateModel">
                    <label>计算模型</label>
                </li>
                <li id="functionModel">
                    <label>功能模型</label>
                </li>
            </ul>
        </div>
    </div>
    <div class="classify">
        <p>二级分类</p>
        <div>
            <ul id="secondLayer" belongDirectory="">
            </ul>

        </div>
    </div>
    <div class="classify">
        <p>三级分类</p>
        <div>
            <ul id="thirdLayer" belongDirectory="">
            </ul>

        </div>
    </div>
</div>

</body>
</html>
