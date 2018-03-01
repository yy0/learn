package com.jriver.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "ServletFilter", urlPatterns = "/**")
public class DemoFilter implements Filter {

    /**
     * Default constructor.
     */
    public DemoFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        System.out.println("ServletFilter destroy");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("ServletFilter do filter before");
        chain.doFilter(request, response);
        System.out.println("ServletFilter do filter after");
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("ServletFilter init");
    }

}