package com.project.servlet;

import com.project.beans.Message;
import com.project.chat.OnlineUsersMap;
import com.project.processing.LoginFormProcessor;
import com.project.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.chat.MessagesDeque.messagesDeque;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OnlineUsersMap.onlineUserMap.remove(request.getSession().getAttribute("login"));
        request.getSession().setAttribute("loggedin", null);
        messagesDeque.addFirst(new Message((String) request.getSession().getAttribute("login"), "left us ;("));
         ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
    }
}