package com.sk.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class webUtils {

    //把Map中的值注入到对应的JavaBean属性中
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            /**
             * 把所有请求都注入到user对象中
             */
            System.out.println("注入之前" + bean);
            BeanUtils.populate(bean, value);
            System.out.println("注入之后" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;

    }

    /**
     * 将字符串转换成int类型
     *
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {

        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
