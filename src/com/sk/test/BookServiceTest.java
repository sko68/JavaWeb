package com.sk.test;

import com.sk.pojo.Book;
import com.sk.pojo.Page;
import com.sk.service.BookService;
import com.sk.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {

    @Before
    public void setUp() throws Exception {
    }

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"张三为什么要吃*","罗翔",new BigDecimal(250),20,10,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(21,"老李疟疾了","赵刚",new BigDecimal(300),50000,5,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(21));
    }

    @Test
    public void queryBook() {
        for (Book queryBook : bookService.queryBook()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50));
    }
}