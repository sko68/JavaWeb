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

public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
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

        page.setUrl("client/bookServlet?action=page");

        // 3.保存page对象到request域中
        req.setAttribute("page", page);

        //4、请求转发到/page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数pageNo和pageSize
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = webUtils.parseInt(req.getParameter("min"), 0);
        int max = webUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2.调用BookService.page(pageNo，pageSize):page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格区间，追加到分页条的地址参数中
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }

        page.setUrl(sb.toString());

        // 3.保存page对象到request域中
        req.setAttribute("page", page);

        //4、请求转发到/page/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
