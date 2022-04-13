package com.lizhi.web;

import com.lizhi.pojo.Method;
import com.lizhi.service.MethodService;
import com.lizhi.service.impl.MethodServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodServlet extends BaseServlet {
    MethodService methodService = new MethodServiceImpl();

    /**
     * 方法唯一性核验
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void verifyMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("唯一性验证");
        String methodCallName = req.getParameter("methodCallName");
        List<Method> methodList = methodService.existMethodCallName(methodCallName);
        if (methodList.size() > 1) {
            // 唯一性验证通过
            resp.getWriter().write("false");
        } else {
            resp.getWriter().write("true");
        }
    }
}
