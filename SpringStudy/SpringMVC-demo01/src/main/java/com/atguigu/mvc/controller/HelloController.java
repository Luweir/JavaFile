package com.atguigu.mvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    // "/"  --->   /WEB-INF/templates/index.html
    @RequestMapping("/")
    public String index() {
        // 返回视图名称  视图名称决定了我们要跳转的页面  返回后被视图解析器thymeleaf进行解析  注意那里的前缀后缀（去掉前缀去掉后缀）
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget() {
        return "target";
    }
}
