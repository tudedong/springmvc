package com.lagou.edu.mvcframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author tudedong
 * @description 真正干活的拦截器
 * @date 2020-05-09 16:10:40
 */
public class SecurityHandlerInterceptor implements HandlerInterceptor{

    //定义有访问权限的用户名
    //private String[] securityValues;
    private Map<Pattern,String[]> securityValueMap;

    //有参构造器
    public SecurityHandlerInterceptor(Map<Pattern, String[]> securityValueMap) {
        this.securityValueMap = securityValueMap;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入 SecurityHandlerInterceptor 拦截器，，，");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //1.先判断有没有@Security注解，即要不要拦截,若为空放行
        if(securityValueMap == null){
            response.getWriter().write("欢迎来到此页面~~~");
            return true;
        }
        //2.判断是不是要拦截的username
        String username = request.getParameter("username");
        String s = request.getRequestURI();

        for(Map.Entry<Pattern,String[]> entry:securityValueMap.entrySet()){
            if(entry.getKey().toString().equals(request.getRequestURI())){
                for(String securityValue:entry.getValue()){
                    if(securityValue.trim().equals(username.trim())){
                        response.getWriter().write("欢迎来到此页面~~~");
                        return true;
                    }
                }
            }else{
                continue;
            }
        }
        response.getWriter().write("用户：【"+username+"】没有请求uri：【"+request.getRequestURI()+"】的访问权限！");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    }
}
