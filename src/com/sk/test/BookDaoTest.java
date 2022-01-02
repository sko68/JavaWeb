package com.sk.test;

import com.sk.dao.BookDao;
import com.sk.dao.impl.BookDaoImpl;
import com.sk.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "今天真好", "16523", new BigDecimal(34), 1000, 0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book( 21,"今天吃*了", "16523", new BigDecimal(34), 1000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook: bookDao.queryBooks()
             ) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book:bookDao.queryForPageItems(0,5))
        System.out.println(book);
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book:bookDao.queryForPageItemsByPrice(1,5,10,50))
            System.out.println(book);
    }
}