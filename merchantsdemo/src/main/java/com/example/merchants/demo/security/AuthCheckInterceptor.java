package com.example.merchants.demo.security;

import com.example.merchants.demo.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * @author YANRUI
 */
/** 注册到spring容器中*/
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {

    /**在http请求真正处理之前，拦截器拦截处理*/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        /** */
        String token = httpServletRequest.getHeader(Constants.TOKEN_STRING);
        if(StringUtils.isEmpty(token)){ //null 或者 ''
            throw new Exception("Header 中缺少" + Constants.TOKEN_STRING + "!");
        }
        if (!token.equals(Constants.TOKEN)){
            throw new Exception("Header 中" + Constants.TOKEN_STRING + "错误!");
        }
        AccessContext.setToken(token); //所有的商户拥有同一个token，实际企业开发，每个企业不同token，映射存在数据库
         return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    /**抛出异常时，也代表一个http请求执行完*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        AccessContext.clearAccessKey();
    }
}
