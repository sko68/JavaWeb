package com.sk.web;

import com.sk.pojo.User;
import com.sk.service.UserService;
import com.sk.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 处理用户注册
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 10:55
 * @Version 1.0
 */
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //检查验证码是否正确
        if ("abc".equalsIgnoreCase(code)) {
            //检查用户名是否可用
            if (userService.existUserName(username)) {
                //不可用
                System.out.println("用户名[" + username + "]已存在");
                //把回显的信息保存到request域中
                req.setAttribute("msg", "验证码错误");
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.setAttribute("email",email);
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            } else {
                //可用，调用Service保存到数据库
                userService.registerUser(new User(null, username, password, email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        } else {
            //把回显的信息保存到request域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("email",email);
            //跳回注册页面
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
