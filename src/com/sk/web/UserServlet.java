package com.sk.web;

import com.sk.pojo.User;
import com.sk.service.UserService;
import com.sk.service.impl.UserServiceImpl;
import com.sk.utils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            //保存用户登录的信息到Session域中
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.销毁Session域中的用户登录信息
        req.getSession().invalidate();
//        2.重定向到首页
        resp.sendRedirect(req.getContextPath());

    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user =  webUtils.copyParamToBean(req.getParameterMap(),new User());


        //2.检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            //3.检查用户名是否可用
            if (userService.existUserName(username)) {
                //不可用
                System.out.println("用户名[" + username + "]已存在");
                //把回显的信息保存到request域中
                req.setAttribute("msg", "验证码错误");
                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.setAttribute("email", email);
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用，调用Service保存到数据库
                userService.registerUser(new User(null, username, password, email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            //把回显的信息保存到request域中
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("email", email);
            //跳回注册页面
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

}
