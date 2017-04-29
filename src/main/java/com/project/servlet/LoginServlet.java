package com.project.servlet;

import com.project.beans.Message;
import com.project.processing.LoginFormProcessor;
import com.project.utils.AuthorizationUtils;
import com.project.utils.ServletUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.chat.MessagesDeque.messagesDeque;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorizationUtils.instanse.clearError(request);
        if ((request.getParameter("login") != null) && (request.getParameter("password") != null)) {
            if (LoginFormProcessor.instanse.login(request)) {
                request.getSession().setAttribute("loggedin", "true");
                request.getSession().setAttribute("login", request.getParameter("login"));
                messagesDeque.addFirst(new Message((String) request.getSession().getAttribute("login"), "joined us :)"));
                ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/index.jsp");
            } else {
                ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
            }
        } else {
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
        }
    }
}