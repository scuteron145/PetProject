 package com.project.processing;

import com.project.beans.User;
import com.project.dao.UserDao;
import com.project.email.EmailSender;
import com.project.utils.AuthorizationUtils;
import com.project.utils.ValidationUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegistrationFormProcessor {

    public boolean registerUser(HttpServletRequest request, ServletContext servletContext) throws ServletException, IOException {
        User user = creatUser(request);
        if(user == null){
            return false;
        }
        UserDao userDao = new UserDao();
        userDao.addUserToDatabase(user);
        EmailSender tlsSender = new EmailSender("chatbyanton@gmail.com", "qwerty4321");
        tlsSender.send("Registration completed successfully",
                "Congratulations you've already been registered. Your login : " + user.getLogin() + "; Your password : " + user.getPassword() + ";",
                user.getEmail());
        return true;
    }

    public User creatUser(HttpServletRequest request){
        if( AuthorizationUtils.instanse.LoginPasswordEmailAreEmpty(request) ){
            AuthorizationUtils.instanse.setErrorEmptyField(request);
            return null;
        }
        String login = null;
        if(AuthorizationUtils.instanse.checkLoginForRegistration(AuthorizationUtils.instanse.readLogin(request), request)){
            login = AuthorizationUtils.instanse.readLogin(request);
        } else {
            return null;
        }
        String password = null;
        if(AuthorizationUtils.instanse.checkPassword(AuthorizationUtils.instanse.readPassword(request), request)){
            password = AuthorizationUtils.instanse.readPassword(request);
        } else {
            return null;
        }
        String email = null;
        if(AuthorizationUtils.instanse.checkEmailForRegistration(AuthorizationUtils.instanse.readEmail(request), request)){
            email = AuthorizationUtils.instanse.readEmail(request);
        } else {
            return null;
        }
        if( (login == null) || (password == null) || (email == null)){
            AuthorizationUtils.instanse.setErrorEmptyField(request);
            return null;
        }
        String sex = null;
        int age = -1;
        String comment = null;
        if(request.getParameter("sex") != null){
            sex = request.getParameter("sex");
        }
        if(request.getParameter("age") != null){
            if(ValidationUtils.instanse.isCorrectAge(request.getParameter("age"))){
                age = Integer.valueOf(request.getParameter("age"));
            }
        }
        if(request.getParameter("comment") != null){
            comment = request.getParameter("comment");
        }
        User user = new User(login, password, email, sex, age, comment );
        return user;
    }
}
