package com.project.processing;

import com.project.beans.User;
import javax.servlet.http.HttpServletRequest;

public class LoginFormProcessor {
    public static LoginFormProcessor instanse = new LoginFormProcessor();

    public boolean login(HttpServletRequest request) {
       /* RegistrationFormProcessor r = new RegistrationFormProcessor();
        String login = r.readNickOrPassword(request,"login");
        String password = r.readNickOrPassword(request,"password");
        if( (login == null) || (password == null) ){
            r.wrongLoginOrPassword(request);
            return false;
        }
        User user = new User(login,password);
        if(OnlineUsersMap.onlineUserMap.containsKey(user.getLogin())){
            r.wrongLoginOrPassword(request);
            return false;
        }
        UserDao userDao = new UserDao();
        if(!userDao.checkIfUserExists(user.getLogin())){
            r.wrongLoginOrPassword(request);
            return false;
        }
        OnlineUsersMap.onlineUserMap.put(user.getLogin(),user);
        return true;*/
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
