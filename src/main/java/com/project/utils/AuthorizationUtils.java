package com.project.utils;


import com.project.dao.UserDao;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationUtils {
    public static AuthorizationUtils instanse = new AuthorizationUtils();

    public boolean LoginPasswordEmailAreEmpty(HttpServletRequest request) {
        if (LoginPasswordAreEmpty(request) ||
                (readEmail(request).equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean LoginPasswordAreEmpty(HttpServletRequest request) {
        if ((readLogin(request).equals("")) ||
                (readPassword(request).equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLoginForRegistration(String login, HttpServletRequest request){
        UserDao userDao = new UserDao();
        try {
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(login,4,20)) ){
                if(!userDao.checkIfUserExists(login)){
                    return true;
                } else {
                    setErrorWrongLoginItIsAlreadyRegistered(request);
                }
            } else {
                setErrorWrongLogin(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLoginForAuthorization(String login, HttpServletRequest request){
        UserDao userDao = new UserDao();
        try {
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(login,4,20)) ){
                if(userDao.checkIfUserExists(login)){
                    return true;
                } else {
                    setErrorNotRegisteredUser(request);
                }
            } else {
                setErrorWrongLogin(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readLogin(HttpServletRequest request){
        if(request.getParameter("login") == null){
            return null;
        }
        return request.getParameter("login");
    }

    public boolean checkPassword(String password, HttpServletRequest request){
        try {
            if( (ValidationUtils.instanse.isCorrectNickOrPassword(password,4,20)) ){
                return true;
            } else {
                setErrorWrongPassword(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readPassword(HttpServletRequest request){
        if(request.getParameter("password") == null){
            return null;
        }
        return request.getParameter("password");
    }

    public boolean checkEmailForRegistration(String email, HttpServletRequest request){
        UserDao userDao = new UserDao();
        try {
            if( (ValidationUtils.instanse.isValidEmailAddress(email)) ){
                if(!userDao.checkIfEmailRegistered(email)){
                    return true;
                } else {
                    setErrorWrongEmailIsAlreadyRegistered(request);
                }
            } else {
                setErrorWrongEmail(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readEmail(HttpServletRequest request){
        if(request.getParameter("email") == null){
            return null;
        }
        return request.getParameter("email");
    }

    public void setErrorEmptyField(HttpServletRequest request){
        String message = "Fill in all necessary text fields to register.";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorWrongLogin(HttpServletRequest request){
        String message = "Wrong login. It should be between 4 & 10 symbols (only English letters or numbers 0-9).";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorWrongLoginItIsAlreadyRegistered(HttpServletRequest request){
        String message = "Wrong login. It is already registered.";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorWrongLoginUserIsOnline(HttpServletRequest request){
        String message = "You are online.";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorWrongPassword(HttpServletRequest request){
        String message = "Wrong password. It should be between 4 & 10 symbols (only English letters or numbers 0-9).";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorAnotherPassword(HttpServletRequest request){
        String message = "The password is incorrect.";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorWrongEmail(HttpServletRequest request){
        String message = "This e-mail address is entered incorrectly.";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorWrongEmailIsAlreadyRegistered(HttpServletRequest request){
        String message = "This e-mail address is already registered.";
        request.getSession().setAttribute("usererror",message);
    }

    public void setErrorNotRegisteredUser(HttpServletRequest request){
        String message = "This login is not registered.";
        request.getSession().setAttribute("usererror",message);
    }
}
