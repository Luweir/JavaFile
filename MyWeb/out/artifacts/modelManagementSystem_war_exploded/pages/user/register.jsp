S<%--
  Created by IntelliJ IDEA.
  User: 46304
  Date: 2021/11/11
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 动态获取url--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<html>
<head>
    <title>模型管理系统</title>
    <base href=<%=basePath%>>
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script>
        $(function () {
            // 验证用户名是否可用
            $("#username").change(function () {
                let username = $(this).val();
                $.ajax({
                    url: "${basePath}userServlet?action=ajaxExistUsername",
                    type: "POST",
                    data: {
                        "username": username,
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.existUsername) {
                            $("span.errorMsg").text("用户名已存在！");
                            // $("span.errorMsg").css("visibility", "unset");

                        } else {
                            $("span.errorMsg").text("");
                            // $("span.errorMsg").css("visibility", "hidden");
                        }
                    },
                })
            })

            // 核对密码
            $("#rePassword").blur(function () {
                let password = $("#password").val();
                let rePassword = $(this).val();
                if (password !== rePassword) {
                    $("span.errorMsg").text("密码不一致！");
                } else {
                    $("span.errorMsg").text("");
                }
            })

            // 给验证码绑定单击事件
            $("#codeImg").click(function () {
                // 在事件响应的函数中有一个 this 对象，这个 this 对象 是当前正在响应事件的 dom 对象
                // src 属性表示验证码 img 标签的图片路径，可读可写
                this.src = "${basePath}kaptcha.jpg?d=" + new Date().getTime();
            })

            // 给注册绑定单击事件
            $("#sub").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                let usernameText = $("#username").val();
                //2 创建正则表达式对象
                let usernamePat = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernamePat.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                let repwdText = $("#rePassword").val();
                //2 和密码相比较
                if (repwdText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("密码不一致！");
                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                //1 获取邮箱里的内容
                let emailText = $("#email").val();
                //2 创建正则表达式对象
                let emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3 使用test方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用户
                    $("span.errorMsg").text("邮箱格式不合法！");
                    return false;
                }

                let codeText = $("#code").val();
                //去掉验证码前后空格
                codeText = $.trim(codeText);
                if (codeText == null || codeText === "") {
                    //4 提示用户
                    $("span.errorMsg").text("验证码不能为空！");

                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");

            });
        })
    </script>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: sans-serif;
            background: url("${basePath}static/img/bgimg1.png");
        }

        .box {
            width: 300px;
            padding: 40px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #19191900;
            text-align: center;
            box-shadow: 20px 25px 45px 15px;
        }

        .box h1 {
            color: white;
            text-transform: uppercase;
            font-weight: 500;
        }

        .box input[type = "submit"] {
            /* border:0;
            */
            background: none;
            display: block;
            margin: 20px auto;
            text-align: center;
            border: 2px solid #2ecc71;
            padding: 14px 40px;
            outline: none;
            color: white;
            border-radius: 24px;
            transition: 0.25s;
            cursor: pointer;
        }

        .box input[type = "submit"]:hover {
            background: #2ecc71;
        }

        a {
            margin: 0 40px;
            color: white;
            text-decoration: none;
        }

        label {
            color: #ffffff;
            font-size: 18px;
        }

        div input {
            margin-left: 20px;
            height: 30px;
        }

        div {
            margin-top: 10px;
        }

        span {
            margin-left: 150px;
            /*visibility: hidden;*/
            color: #fe3b49;
        }

        #codeImg {
            width: 90px;
            height: 30px;
            float: left;
            margin-left: 10px;
        }

        .code_div {
            overflow: hidden;
        }

        #code {
            width: 80px;
            float: left;
        }
    </style>
</head>
<body>
<form class="box" action="userServlet?action=register" method="post">
    <h1>用户注册</h1>
    <span class="errorMsg">${empty requestScope.msg?"":requestScope.msg}</span>
    <div>
        <label>用户名称</label>
        <input type="text" name="username" placeholder="Username" value="${requestScope.username}" id="username">
    </div>
    <div>
        <label>用户密码</label>
        <input type="password" name="password" id="password" placeholder="Password">
    </div>
    <div>
        <label>确认密码</label>
        <input type="password" name="password" id="rePassword" placeholder="Password">
    </div>
    <div>
        <label>邮箱地址</label>
        <input type="text" name="Email" id="email" value="${requestScope.email}" placeholder="Email">
    </div>
    <div class="code_div">
        <label style="float: left">&emsp;验证码&emsp;</label>
        <input type="text" name="code" id="code">
        <img id="codeImg" src="kaptcha.jpg">
    </div>
    <input type="submit" id="sub" value="立即注册">
</form>
</body>
</html>