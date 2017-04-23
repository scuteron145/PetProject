package com.project.servlet;

import com.project.email.EmailSender;
import com.project.processing.LoginFormProcessor;
import com.project.utils.ServletUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((request.getParameter("login") != null) && (request.getParameter("password") != null)) {
            doPost(request, response);
        }
        ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (LoginFormProcessor.instanse.login(request)) {
            request.getSession().setAttribute("login", request.getParameter("login"));
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/index.jsp");
        } else {
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
        }
    }

}