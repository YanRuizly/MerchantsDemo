package com.example.merchants.demo.security;

/**
 * 用ThreadLocal 去单独存储每一个线程携带的token信息
 * @author YANRUI
 */
public class AccessContext {
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static String getToken(){
        return token.get();
    }

    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }
    /** 清除当前线程的token*/
    public static void clearAccessKey(){
        token.remove();
    }
}
