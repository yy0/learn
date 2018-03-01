package com.jriver.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DemoListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DemoListener() {
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent contextEvent)  {
         System.out.println("ContextListener destroy");
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent contextEvent)  {
        System.out.println("ContextListener init");
    }

}