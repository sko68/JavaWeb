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
 * @Description: TODO
 * @Author SK
 * @Email sk16168@163.com
 * @Date 2021/12/4 13:58
 * @Version 1.0
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.调用userService.login()登录
        User loginUser = userService.login(new User(null, username, password, null));
        //如果等于null，说明登录失败
        if (loginUser == null) {
            //把错误信息，和回显的表单信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        } else {
            //登录成功

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

    }
}
