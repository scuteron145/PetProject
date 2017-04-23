package com.project.processing;

import com.project.beans.User;
import com.project.chat.OnlineUsersMap;
import com.project.dao.UserDao;
import com.project.utils.AuthorizationUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginFormProcessor {
    public static LoginFormProcessor instanse = new LoginFormProcessor();
    public boolean login(HttpServletRequest request) {
        if( AuthorizationUtils.instanse.LoginPasswordAreEmpty(request) ){
            AuthorizationUtils.instanse.setErrorEmptyField(request);
            return false;
        }
        String login = null;
        if(AuthorizationUtils.instanse.checkLoginForAuthorization(AuthorizationUtils.instanse.readLogin(request), request)){
            login = AuthorizationUtils.instanse.readLogin(request);
        } else {
            return false;
        }
        String password = null;
        if(AuthorizationUtils.instanse.checkPassword(AuthorizationUtils.instanse.readPassword(request), request)){
            password = AuthorizationUtils.instanse.readPassword(request);
        } else {
            return false;
        }
        if(login == null){
            AuthorizationUtils.instanse.setErrorWrongLogin(request);
            return false;
        }
        if(password == null){
            AuthorizationUtils.instanse.setErrorWrongPassword(request);
            return false;
        }
        if(OnlineUsersMap.onlineUserMap.containsKey(login)){
            AuthorizationUtils.instanse.setErrorWrongLoginUserIsOnline(request);
            return false;
        }
        UserDao userDao = new UserDao();
        User user = null;
        if(!userDao.checkIfUserExists(login)){
            AuthorizationUtils.instanse.setErrorNotRegisteredUser(request);
            return false;
        } else {
            user = userDao.getUserByLogin(login);
        }
        if(password.equals(user.getPassword())){
            OnlineUsersMap.onlineUserMap.put(login, user);
            return true;
        } else {
            AuthorizationUtils.instanse.setErrorAnotherPassword(request);
            return false;
        }
    }

    public boolean validate(User user, String password) {
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
