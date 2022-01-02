package com.sk.web;

import com.sk.pojo.Book;
import com.sk.pojo.Cart;
import com.sk.pojo.CartItem;
import com.sk.service.BookService;
import com.sk.service.impl.BookServiceImpl;
import com.sk.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数，商品编号
        int id = webUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转换成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回商品原来列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = webUtils.parseInt(req.getParameter("id"),0);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            //删除了购物车商品项
            cart.deleteItem(id);
        }
        //重定向回原来购物车展示页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //清空购物车
            cart.clear();
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数，商品编号，商品数量
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        int count = webUtils.parseInt(req.getParameter("count"), 1);
        //获取cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            //修改商品数量
            cart.updateCount(id, count);
            //重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }
}
