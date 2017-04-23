package com.project.utils;


import com.project.dao.UserDao;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationUtils {
    public static AuthorizationUtils instanse = new AuthorizationUtils();

    public String readLogin(HttpServletRequest request){
        if(request.getParameter("login") == null){
            return null;
        }
        String login = request.getParameter("login");
        UserDao userDao = new UserDao();
        try {
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(login,4,20)) ){
                if(!userDao.checkIfUserExists(login)){
                    return login;
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
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(password,4,20)) ){
                return password;
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
                    return email;
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

    public void setErrorEmptyField(HttpServletRequest request){
        String message = "Fill in all necessary text fields to register.";
        request.getSession().setAttribute("userError",message);
    }

    public void setErrorWrongLogin(HttpServletRequest request){
        String message = "Wrong login. It should be between 4 & 10 symbols (only English letters or numbers 0-9).";
        request.getSession().setAttribute("userError",message);
    }

    public void setErrorWrongLoginItIsAlreadyRegistered(HttpServletRequest request){
        String message = "Wrong login. It is already registered.";
        request.getSession().setAttribute("userError",message);
    }

    public void setErrorWrongPassword(HttpServletRequest request){
        String message = "Wrong password. It should be between 4 & 10 symbols (only English letters or numbers 0-9).";
        request.getSession().setAttribute("userError",message);
    }

    public void setErrorWrongEmail(HttpServletRequest request){
        String message = "This e-mail address is entered incorrectly.";
        request.getSession().setAttribute("userError",message);
    }

    public void setErrorWrongEmailIsAlreadyRegistered(HttpServletRequest request){
        String message = "This e-mail address is already registered.";
        request.getSession().setAttribute("userError",message);
    }

    public void setErrorNotRegisteredUser(HttpServletRequest request){
        String message = "This login is not registered.";
        request.getSession().setAttribute("userError",message);
    }
}
