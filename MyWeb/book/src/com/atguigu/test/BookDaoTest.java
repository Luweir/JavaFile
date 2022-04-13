package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.lang.annotation.Inherited;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookdao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookdao.addBook(new Book(null, "国哥好帅", "19225", new BigDecimal(999), 1111111, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookdao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookdao.updateBook(new Book(21, "大家都可以帅", "1000", new BigDecimal(999), 1111111, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookdao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookdao.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookdao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookdao.queryForPageTotalCountByPrice(0, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookdao.queryForPageItemsByPrice(1, 4, 0, 50)) {
            System.out.println(book);
        }
    }
}