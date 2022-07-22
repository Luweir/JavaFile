package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 总记录数
        int pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        int pageTotal = (pageTotalCount - 1) / pageSize + 1;
        page.setPageTotal(pageTotal);
        // 设置当前页码，里面进行数据边界有效性检查
        page.setPageNo(pageNo);
        // 设置每页显示数量
        page.setPageSize(pageSize);


        // 当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice) {
        Page<Book> page = new Page<>();
        // 总记录数
        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(minPrice, maxPrice);
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        int pageTotal = (pageTotalCount - 1) / pageSize + 1;
        page.setPageTotal(pageTotal);
        // 设置当前页码，里面进行数据边界有效性检查
        page.setPageNo(pageNo);
        // 设置每页显示数量
        page.setPageSize(pageSize);


        // 当前页数据
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize, minPrice, maxPrice);
        page.setItems(items);
        return page;
    }
}
