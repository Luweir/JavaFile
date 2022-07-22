package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 不为空 清空购物车
            cart.clear();
            // 切记不能直接摧毁所有session
            // req.getSession().invalidate();

        }
        // 重定向回之前页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取 session 中的购物车信息
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 不为空 删除
            cart.deleteItem(id);
//            req.getSession().setAttribute("cart", cart);
        }
        // 重定向回之前页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改制定id的数量
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id, count);
        }
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 2 调用bookservice.queryBookById(id ) : Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 3 把图书信息，转换成为cartItem商品项
        // 先看购物车有没有 没有先new 一个，有直接用

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            // 5 保存到域session中
            req.getSession().setAttribute("cart", cart);
            req.getSession().setMaxInactiveInterval(60 * 7);
        }
        // 4 调用cart.addItem( cartitem);添加商品项
        cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));

        System.out.println(cart);
        //重定向回原来商品所在列表页面，地址栏用原来的
        resp.sendRedirect(req.getHeader("Referer"));

    }
}
