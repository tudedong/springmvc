package com.lagou.demo.controller;

import com.lagou.demo.service.IDemoService;
import com.lagou.edu.mvcframework.annotations.LagouAutowired;
import com.lagou.edu.mvcframework.annotations.LagouController;
import com.lagou.edu.mvcframework.annotations.LagouRequestMapping;
import com.lagou.edu.mvcframework.annotations.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@LagouController
@LagouRequestMapping("/demo")
@Security("admin")
public class DemoController {


    @LagouAutowired
    private IDemoService demoService;


    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param username
     * @return
     */
    @LagouRequestMapping("/handle00")
    public String handle00(HttpServletRequest request, HttpServletResponse response,String username) {
        return demoService.get(username);
    }

    @LagouRequestMapping("/handle01")
    @Security(value = {"aaa"})
    public String handle01(HttpServletRequest request, HttpServletResponse response,String username) {
        return demoService.get(username);
    }

    @LagouRequestMapping("/handle02")
    @Security(value = {"bbb","ccc"})
    public String handle02(HttpServletRequest request, HttpServletResponse response,String username) {
        return demoService.get(username);
    }

}
