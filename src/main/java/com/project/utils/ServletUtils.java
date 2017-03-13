package com.project.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtils{
    public static ServletUtils instanse = new ServletUtils();

    public void redirect(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher(request.getContextPath() + url);
        dispatcher.forward(request, response);
    }

}