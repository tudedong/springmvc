package com.lagou.edu.mvcframework.pojo;

import com.lagou.edu.mvcframework.interceptor.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 封装handler方法相关的信息
 */
public class Handler {

    // method.invoke(obj,)
    private Object controller;

    private Method method;

    // spring中url是支持正则的
    private Pattern pattern;

    // 参数顺序,是为了进行参数绑定，key是参数名，value代表是第几个参数 <name,2>
    private Map<String,Integer> paramIndexMapping;

    //针对每个请求url的拦截器，因为@Security注解在Controller类或者Handler方法上，所有多个拦截器
    //private List<HandlerInterceptor> handlerInterceptorList = new ArrayList<>();
    private Map<Pattern,HandlerInterceptor> handlerInterceptorMap;

    public Handler(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        this.paramIndexMapping = new HashMap<>();
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Map<String, Integer> getParamIndexMapping() {
        return paramIndexMapping;
    }

    public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
        this.paramIndexMapping = paramIndexMapping;
    }

    public Map<Pattern, HandlerInterceptor> getHandlerInterceptorMap() {
        return handlerInterceptorMap;
    }

    public void setHandlerInterceptorMap(Map<Pattern, HandlerInterceptor> handlerInterceptorMap) {
        this.handlerInterceptorMap = handlerInterceptorMap;
    }

    /**
     * 对外提供添加拦截器到handler
     * @param handlerInterceptors
     */
    /*public void addHandlerInterceptor(List<HandlerInterceptor> handlerInterceptors){
        this.handlerInterceptorList.addAll(handlerInterceptors);
    }*/

    /**
     * 循环遍历并执行拦截器
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public boolean doPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(handlerInterceptorMap != null ){
            for(Map.Entry<Pattern,HandlerInterceptor> entry:handlerInterceptorMap.entrySet()){
                if(entry.getKey().toString().equals(request.getRequestURI())){
                    return entry.getValue().preHandle(request,response,this.controller);
                }
            }
        }
        return true;
    }
}
