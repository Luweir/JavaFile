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

public class ClientBookServlet extends BaseServlet {
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
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        // 默认每页显示 PAGE_SIZE 个记录
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 2调用Bookservice.page( pageNo,pageSize): Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        // 设置前台url
        page.setUrl("client/bookServlet?action=page");

        // 3保存Page对象到Request域中
        req.setAttribute("page", page);
        // 4请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 处理 按价格查询以及之后的分页 功能，如果没输入价格，点了查询，也按升序显示，可以理解
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1获取请求的参数pageNo和pagesize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        // 默认每页显示 PAGE_SIZE 个记录
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 取出最小价格和最大价格
        int minPrice = WebUtils.parseInt(req.getParameter("minPrice"), 0);
        int maxPrice = WebUtils.parseInt(req.getParameter("maxPrice"), Integer.MAX_VALUE);

        // 2调用Bookservice.page( pageNo,pageSize): Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, minPrice, maxPrice);

        // 设置前台url
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格的参数，追加到分页条的参数中
        if (req.getParameter("minPrice") != null) {
            sb.append("&minPrice=").append(req.getParameter("minPrice"));
        }
        // 最大价格亦是如此
        if (req.getParameter("maxPrice") != null ) {
            sb.append("&maxPrice=").append(req.getParameter("maxPrice"));
        }

        page.setUrl(sb.toString());

        // 3保存Page对象到Request域中
        req.setAttribute("page", page);
        // 4请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
