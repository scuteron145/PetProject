package com.project.processing;

import com.project.beans.User;
import com.project.dao.UserDao;
import com.project.utils.XmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindFormProcessor {
    public static FindFormProcessor instanse = new FindFormProcessor();
    public void findUsers(HttpServletRequest request){
        UserDao userDao = new UserDao();
        List<User> foundUsers = userDao.getUsersByAPieceOfLogin(request.getParameter("find"));
        String users = XmlUtils.instanse.generateStringFromXml(XmlUtils.instanse.listOfUsersToDoc(foundUsers));
        request.getSession().setAttribute("foundusers", users);
    }
}
