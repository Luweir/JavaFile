package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    /**
     * ajax 验证用户名是否可用
     *
     * @param req
     * @param resp
     */
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("进入 ajaxExistUsername");
        String username = req.getParameter("username");
        boolean res = userService.existUsername(username);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername", res);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * 处理登陆的功能
     *
     * @param req
     * @param resp
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 1 获取参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            // 2 调用Service层，看用户名密码对应上的
            User loginUser = this.userService.login(new User(null, username, password, null));
            if (null != loginUser) {
                // 3 正确 跳转到登陆成功页面
                System.out.println("登陆成功");
                req.getSession().setAttribute("user", loginUser);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
            } else {
                // 4 失败，跳转到登陆页面
                // 把错误信息，和回显的表单信息保存到request域中
                req.setAttribute("msg", "用户名或密码错误!");
                req.setAttribute("username", username);

                System.out.println("用户名或密码不正确");
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注销登陆
     *
     * @param req
     * @param resp
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //  1、销毁session中用户登录的信息(或者销毁session）
        req.getSession().invalidate();
        //  2、重定向到首页(或登录页面)。
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册的功能
     *
     * @param req
     * @param resp
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) {

        // 获取 google 验证码并删除Session
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        try {
            // 2、检查验证码：
            // 忽略大小写
            if (token != null && token.equalsIgnoreCase(code)) {
                // 3、检查用户名是否可用
                if (userService.existUsername(username)) {
                    System.out.println("用户名已存在");

                    req.setAttribute("msg", "用戶名已存在！");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                } else {
                    // 4、可用，存数据库
                    userService.registerUser(new User(null, username, password, email));
                    // 跳转到注册成功
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                }
            } else {
                // 不通过，调回注册页面
                System.out.println("验证码错误 " + code);
                req.setAttribute("msg", "验证码错误！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
