package com.sk.web;

import com.sk.pojo.Book;
import com.sk.pojo.Page;
import com.sk.service.BookService;
import com.sk.service.impl.BookServiceImpl;
import com.sk.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();



    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //1.获取请求的参数--封装成Book对象
        Book book = webUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2.调用BookService.addBook()保存图书
        bookService.addBook(book);

        //3.跳到图书列表页面
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1。获取请求的参数id,图书编号
        int id = webUtils.parseInt(req.getParameter("id"),0);

        //2.调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);

        //3.重定向回图书列表管理
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数==封装成Book对象
        Book book = webUtils.copyParamToBean(req.getParameterMap(), new Book());

        //2.调用BookService.updateBook(book) ;//修改图书
        bookService.updateBook(book);

        //3.重定向回图书列表管理页面
        //地址 /工程名/manager/bookServlet?action?=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书编号
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3.保存图书到request中
        req.setAttribute("book",book);
        //4.请求转发到pages/manager/book_editor.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }


    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、通过BookService查询全部图书
        List<Book> books = bookService.queryBook();
        //2、把全部图书保存在request域中
        req.setAttribute("books", books);
        //3、请求转发到/page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用BookService.page(pageNo，pageSize):page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        page.setUrl("manager/bookServlet?action=page");

        // 3.保存page对象到request域中
        req.setAttribute("page", page);

        //4、请求转发到/page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);


    }
}
