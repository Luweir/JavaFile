package com.lizhi.web;


import com.google.gson.Gson;
import com.lizhi.pojo.User;
import com.lizhi.service.UserService;
import com.lizhi.service.impl.UserServiceImpl;
import com.lizhi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    /**
     * 删除用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        if (userId != 0) {
            userService.deleteUser(userId);
        } else {
            resp.getWriter().write("false");
        }
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxUpdateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        if (userId > 0) {
            User user = userService.getUserByUserId(userId);
            // 如果是恢复初始密码
            if (req.getParameter("initPws") != null) {
                user.setPassword("123456");
                resp.getWriter().write("true");
            } else {
                String realName = req.getParameter("realName");
                int permissions = WebUtils.parseInt(req.getParameter("permissions"), 0);
                String department = req.getParameter("department");
                user.setRealName(realName);
                if (permissions != 0) {
                    user.setPermissions(permissions);
                    user.setDepartment(department);
                    userService.updateUser(user);
                    resp.getWriter().write("true");
                }
            }

        }
    }

    /**
     * 用户权限管理
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void userManagement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userService.getAllUsers();
        List<User> users = new ArrayList<>();

        String username = req.getParameter("username") == "" ? null : req.getParameter("username");
        String realName = req.getParameter("realName") == "" ? null : req.getParameter("realName");
        String department = req.getParameter("department") == "" ? null : req.getParameter("department");
        int permissions = req.getParameter("permissions") == "" ? 0 : WebUtils.parseInt(req.getParameter("permissions"), 0);

        userList.forEach(item -> {
            if (username == null || username.equals(item.getUsername())) {
                if (realName == null || realName.equals(item.getRealName())) {
                    if ((department == null || department.equals(item.getDepartment()) && (permissions == 0 || permissions == item.getPermissions())))
                    {
                        users.add(item);
                    }
                }
            }
        });
        req.setAttribute("users", users);
        req.getRequestDispatcher("/pages/manage/user_management.jsp").forward(req, resp);
    }

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
     * 获取用户详情，提供给用户修改自己的信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void personalCenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

    }

    /**
     * 修改用户信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("修改用户信息");
        User user = (User) req.getSession().getAttribute("user");
        user.setDepartment(req.getParameter("department"));
        user.setEmail(req.getParameter("email"));
        user.setTelephone(req.getParameter("telephone"));
        user.setRealName(req.getParameter("realName"));
        if (req.getParameter("password") != null) {
            user.setPassword(req.getParameter("password"));
        }
        userService.updateUser(user);
        req.getSession().setAttribute("user", user);
        // 重定向回之前页面
        resp.getWriter().write("true");
    }

    /**
     * 处理登陆的功能
     *
     * @param req
     * @param resp
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            resp.sendRedirect(req.getContextPath() + "/pages/client/index.jsp");
            return;
        }
        try {
            // 1 获取参数
            System.out.println("登陆后台");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            // 2 调用Service层，看用户名密码对应上的
            User loginUser = this.userService.login(new User(null, username, password, null, null, null, null, null));
            if (null != loginUser) {
                // 3 正确 跳转到登陆成功页面
                System.out.println("登陆成功");
                // 放入session域中
                req.getSession().setAttribute("user", loginUser);
                // 建立一个cookie让浏览器保存，覆盖掉存活时间为-1的JSESSIONID（记录了sessionId）
                Cookie cookie = new Cookie("JSESSIONID", req.getSession().getId());
                cookie.setPath(req.getContextPath() + "/");
                // cookie存活一个小时
                cookie.setMaxAge(60 * 60);
                // 把cookie放浏览器
                resp.addCookie(cookie);
                resp.sendRedirect(req.getContextPath() + "/pages/client/index.jsp");
            } else {
                // 4 失败，跳转到登陆页面
                // 把错误信息，和回显的表单信息保存到request域中
                req.setAttribute("msg", "用户名或密码错误!");
                req.setAttribute("username", username);

                System.out.println("用户名或密码不正确");
                req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            }
        } catch (IOException | ServletException e) {
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
        resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");

    }

    /**
     * 处理注册的功能
     *
     * @param req
     * @param resp
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) {

        // 获取 google 验证码并删除Session
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 1、获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        try {
            // 2、检查验证码：忽略大小写
            if (token != null && token.equalsIgnoreCase(code)) {
                // 3、检查用户名是否可用
                if (userService.existUsername(username)) {
                    System.out.println("用户名已存在");

                    req.setAttribute("msg", "用户名已存在！");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
                } else {
                    // 4、可用，存数据库
                    User user = new User(null, username, password, 1, null, null, null, email);
                    userService.registerUser(user);
                    user = userService.getUserByUsername(username);
                    req.getSession().setAttribute("user", user);
                    // 建立一个cookie让浏览器保存，覆盖掉存活时间为-1的JSESSIONID（记录了sessionId）
                    Cookie cookie = new Cookie("JSESSIONID", req.getSession().getId());
                    cookie.setPath(req.getContextPath() + "/");
                    // cookie存活一个小时
                    cookie.setMaxAge(60 * 60);
                    // 把cookie放浏览器
                    resp.addCookie(cookie);
                    // 跳转到主页
                    resp.sendRedirect(req.getContextPath() + "/pages/client/index.jsp");
                }
            } else {
                // 不通过，调回注册页面
                System.out.println("验证码错误 " + code);
                req.setAttribute("msg", "验证码错误！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
