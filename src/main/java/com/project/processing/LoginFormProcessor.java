package com.project.processing;

import com.project.beans.User;
import com.project.chat.OnlineUsersMap;
import com.project.dao.UserDao;
import com.project.utils.AuthorizationUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginFormProcessor {
    public static LoginFormProcessor instanse = new LoginFormProcessor();

    public boolean login(HttpServletRequest request) {
        String login = AuthorizationUtils.instanse.readLogin(request);
        String password = AuthorizationUtils.instanse.readPassword(request);
        if(login == null){
            AuthorizationUtils.instanse.setErrorWrongLogin(request);
            return false;
        }
        if(password == null){
            AuthorizationUtils.instanse.setErrorWrongPassword(request);
            return false;
        }
        if(OnlineUsersMap.onlineUserMap.containsKey(login)){
            AuthorizationUtils.instanse.setErrorWrongLoginItIsAlreadyRegistered(request);
            return false;
        }
        UserDao userDao = new UserDao();

        if(!userDao.checkIfUserExists(login)){
            AuthorizationUtils.instanse.setErrorWrongLoginItIsAlreadyRegistered(request);
            return false;
        }
        //OnlineUsersMap.onlineUserMap.put(user.getLogin(),user);
        return true;
    }

    public boolean validate(User user, String password) {
        if (user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
