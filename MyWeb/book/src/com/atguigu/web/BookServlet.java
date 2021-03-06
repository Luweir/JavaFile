package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 编写与实际页面上有的功能
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1获取请求的参数pageNo和pagesize
        // System.out.println(req.getParameter("pageNo"));
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        // 默认每页显示 PAGE_SIZE 个记录
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2调用Bookservice.page( pageNo,pageSize): Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        // 设置后台url
        page.setUrl("manager/bookServlet?action=page");

        // 3保存Page对象到Request域中
        req.setAttribute("page", page);
        // 4请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        // 获取请求的参数，封装成 Book 对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用BookService.addBook() 保存图书
        bookService.addBook(book);
        // 调到图书列表页面
        // 不能用请求转发，要用重定向，防止表单重复提交
        // req.getRequestDispatcher("manager/bookServlet?action=list").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取请求的参数 Id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用 bookService.delete 删除图书
        bookService.deleteBookById(id);
        // 重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 获取 指定ID 的book 的信息
     *
     * @param req
     * @param resp
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService.queryBookById 查询图书
        Book book = bookService.queryBookById(id);
        // 保存图书到 request 域中
        req.setAttribute("book", book);
        // 请求转发到 pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取请求的参数 == 封装成Book对象,注意 id 也要放进去，因为 updateBook 是通过 id 查找再修改的
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用updateBook 方法修改图书
        bookService.updateBook(book);
        // 重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void query(HttpServletRequest req, HttpServletResponse resp) {

    }

    /**
     * 查询所有图书并请求转发到book_manage页面
     *
     * @param req
     * @param resp
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }


}
