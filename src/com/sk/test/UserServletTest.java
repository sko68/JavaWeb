package com.sk.test;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login() {
        System.out.println("这是login()方法调用了");
    }

    public void register() {
        System.out.println("这是register()方法调用了");

    }


    public static void main(String[] args) {
        String action = "register";
        try {

            //获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(method);

            //调用目标业务方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
