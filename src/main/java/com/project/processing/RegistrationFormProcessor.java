 package com.project.processing;

import com.project.beans.User;
import com.project.utils.ValidationUtils;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegistrationFormProcessor {

    public boolean registerUser(HttpServletRequest request, ServletContext servletContext) throws ServletException, IOException {
        request.getSession().setAttribute("wrongLoginOrPassword",null);
        User user = creatUser(request);
        if(user == null){
            wrongLoginOrPassword(request);
            return false;
        }
        /*UserDao userDao = new UserDao();
        if(userDao.checkIfUserExists(user.getLogin())){
            wrongLoginOrPassword(request);
            return false;
        }
        userDao.addUserToDatabase(user);*/
        return true;
    }

    public User creatUser(HttpServletRequest request){
        String login = readNickOrPassword(request,"login");
        String password = readNickOrPassword(request,"password");
        if( (login == null) || (password == null) ){
            return null;
        }
        boolean isOnline = true;
        String sex = null;
        int age = -1;
        String comment = null;
        String email = null;
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
        if(request.getParameter("email") != null){
            email = request.getParameter("email");
        }
        User user = new User(login, password, isOnline, sex, age, comment, email);
        return user;
    }

    public String readNickOrPassword(HttpServletRequest request, String requestParameterName){
        if(request.getParameter(requestParameterName) == null){
            return null;
        }
        String password = request.getParameter(requestParameterName);
        try {
            if(ValidationUtils.instanse.isCorrectNickOrPassword(request.getParameter(requestParameterName),4,10)){
                return request.getParameter(requestParameterName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void wrongLoginOrPassword(HttpServletRequest request){
            String message = "Login or password should be between 4 & 10 symbols (only English letters or numbers 0-9).";
            request.getSession().setAttribute("wrongLoginOrPassword",message);
    }
}
