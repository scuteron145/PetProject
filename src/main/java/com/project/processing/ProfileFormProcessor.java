package com.project.processing;

import com.project.beans.User;
import com.project.chat.OnlineUsersMap;
import com.project.dao.UserDao;
import com.project.utils.AuthorizationUtils;
import com.project.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;

public class ProfileFormProcessor {
    public static ProfileFormProcessor instanse = new ProfileFormProcessor();

    public void viewUsersOwnProfile(HttpServletRequest request){
        User user = OnlineUsersMap.onlineUserMap.get(request.getSession().getAttribute("login"));
        request.setAttribute("email", user.getEmail());
        request.setAttribute("sex", user.getSex());
        request.setAttribute("age", user.getAge());
        request.setAttribute("comment", user.getComment());
    }

    public void editUsersProfile(HttpServletRequest request){
        User user = OnlineUsersMap.onlineUserMap.get(request.getSession().getAttribute("login"));
        UserDao userDao = new UserDao();
        boolean changes = false;
        if(request.getParameter("email") != ""){
            String email = request.getParameter("email");
            if(AuthorizationUtils.instanse.checkEmailForRegistration(email, request)){
                user.setEmail(email);
                userDao.updateUsersEmail(user.getLogin(), email);
                changes = true;
            }
        }
        if(!request.getParameter("sex").equals(user.getSex())){
            user.setSex(request.getParameter("sex"));
            userDao.updateUsersSex(user.getLogin(), user.getSex());
            changes = true;
        }
        if(request.getParameter("age") != ""){
            if(ValidationUtils.instanse.isCorrectAge(request.getParameter("age"))){
                user.setAge(Integer.valueOf(request.getParameter("age")));
                userDao.updateUsersAge(user.getLogin(), user.getAge());
                changes = true;
            }
        }
        if(request.getParameter("comment") != ""){
            user.setComment(request.getParameter("comment"));
            userDao.updateUsersComment(user.getLogin(), user.getComment());
            changes = true;
        }
        if(changes){
            OnlineUsersMap.onlineUserMap.put(user.getLogin(), user);
        }
    }


}
