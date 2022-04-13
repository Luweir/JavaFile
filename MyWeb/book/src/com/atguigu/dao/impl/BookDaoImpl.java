package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales` ,`stock`,`img_path`)" +
                "VALUES(?,?,?,?,?,?)";
        int count = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return 0;
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        int count = update(sql, id);
        return count;
    }


    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        int count = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return count;
    }

    @Override
    public Book queryBookById(Integer id) {
        // 注意img_path 要 别名
        String sql = "select `id`,`name`,`author`,`price`,`sales` ,`stock`,`img_path` imgPath from t_book where id=?";

        return queryForOne(sql, Book.class, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales` ,`stock`,`img_path` imgPath from t_book";
        return queryForList(sql, Book.class);
    }

    /**
     * 求总记录数
     *
     * @return
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) getSpecialValues(sql);
        return count.intValue();
    }

    @Override
    public int queryForPageTotalCountByPrice(int minPrice, int maxPrice) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) getSpecialValues(sql, minPrice, maxPrice);
        return count.intValue();
    }


    /**
     * 获得当前页数据
     *
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";

        return queryForList(sql, Book.class, begin, pageSize);
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int minPrice, int maxPrice) {
        // 一般搜索之后，建议排个序
        String sql = "select `id`,`name`,`author`,`price`,`sales` ,`stock`,`img_path` imgPath " +
                "from t_book where price between ? and ? order by price limit ?, ?";
        return queryForList(sql, Book.class, minPrice, maxPrice, begin, pageSize);
    }
}
