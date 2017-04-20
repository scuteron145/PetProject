 package com.project.processing;

import com.project.beans.User;
import com.project.dao.UserDao;
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
        return true;
    }


    public User creatUser(HttpServletRequest request){
        String login = readLogin(request);
        String password = readPassword(request);
        String email = readEmail(request);
        if( (login == null) || (password == null) || (email == null)){
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

    public String readLogin(HttpServletRequest request){
        if(request.getParameter("login") == null){
            return null;
        }
        String login = request.getParameter("login");
        UserDao userDao = new UserDao();
        try {
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(login,4,10)) ){
                if(userDao.checkIfUserExists(login)){
                    return request.getParameter(login);
                } else {
                    setErrorWrongLoginItIsAlreadyRegistered(request);
                }
            } else {
                setErrorWrongLogin(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readPassword(HttpServletRequest request){
        if(request.getParameter("password") == null){
            return null;
        }
        String password = request.getParameter("password");
        try {
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(password,4,10)) ){
                return request.getParameter(password);
            } else {
                setErrorWrongPassword(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readEmail(HttpServletRequest request){
        if(request.getParameter("email") == null){
            return null;
        }
        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        try {
            if( (ValidationUtils.instanse.isValidEmailAddress(email)) ){
                if(!userDao.checkIfEmailRegistered(email)){
                    return request.getParameter(email);
                } else {
                    setErrorWrongEmailIsAlreadyRegistered(request);
                }
            } else {
                setErrorWrongEmail(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setErrorWrongLogin(HttpServletRequest request){
            String message = "Wrong login. It should be between 4 & 10 symbols (only English letters or numbers 0-9).";
            request.getSession().setAttribute("wrongLoginOrPassword",message);
    }

    public void setErrorWrongLoginItIsAlreadyRegistered(HttpServletRequest request){
        String message = "Wrong login. It is already registered.";
        request.getSession().setAttribute("wrongLoginOrPassword",message);
    }

    public void setErrorWrongPassword(HttpServletRequest request){
        String message = "Wrong password. It should be between 4 & 10 symbols (only English letters or numbers 0-9).";
        request.getSession().setAttribute("wrongLoginOrPassword",message);
    }

    public void setErrorWrongEmail(HttpServletRequest request){
        String message = "This e-mail address is entered incorrectly.";
        request.getSession().setAttribute("wrongLoginOrPassword",message);
    }

    public void setErrorWrongEmailIsAlreadyRegistered(HttpServletRequest request){
        String message = "This e-mail address is already registered.";
        request.getSession().setAttribute("wrongLoginOrPassword",message);
    }
}
