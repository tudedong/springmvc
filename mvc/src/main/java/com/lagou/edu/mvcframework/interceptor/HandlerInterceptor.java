package com.lagou.edu.mvcframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tudedong
 * @description 定义一个拦截器组件
 * @date 2020-05-09 16:42:01
 */
public interface HandlerInterceptor {

    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

}
