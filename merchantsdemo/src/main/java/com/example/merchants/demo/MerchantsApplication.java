package com.example.merchants.demo;

import com.example.merchants.demo.security.AuthCheckInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@SpringBootApplication
public class MerchantsApplication extends WebMvcConfigurerAdapter{
	@Resource
	private AuthCheckInterceptor authCheckInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(MerchantsApplication.class, args);
	}

	/** 自定义拦截器，不能什么请求都拦截 */
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		/**
		 * 1、注册拦截器
		 * 2、配置拦截器拦截的http请求
		 * 3、拦截器生效，再验证
		 * */
		registry.addInterceptor(authCheckInterceptor)
				.addPathPatterns("/merchants/**");
		super.addInterceptors(registry);
	}
}
