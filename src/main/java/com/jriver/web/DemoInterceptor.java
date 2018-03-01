package com.jriver.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object o) throws Exception {
        System.out.println("Interceptor preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse rep, Object o, ModelAndView view)
            throws Exception {
        System.out.println("Interceptor postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse rep, Object o, Exception e)
            throws Exception {
        System.out.println("Interceptor afterCompletion");

    }

}