package com.project.servlet;

import com.project.processing.RegistrationFormProcessor;
import com.project.utils.ServletUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationFormProcessor registretionFormProcessor = new RegistrationFormProcessor();
        if(registretionFormProcessor.registerUser(request, getServletContext())){
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/login.jsp");
        } else {
            ServletUtils.instanse.redirect(request, response, getServletContext(), "/WEB-INF/views/registration.jsp");
        }
    }

}