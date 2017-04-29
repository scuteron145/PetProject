package com.project.chat;

import com.project.beans.User;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineUsersMap {
    public static ConcurrentHashMap<String, User> onlineUserMap = new ConcurrentHashMap(); //key - login

    public static String getOnlineUserLoginsThroughTheSpaceLastSpaceIncluding(){
        StringBuilder result = new StringBuilder();
        Set<String> logins = onlineUserMap.keySet();
        for (String i : logins){
            result.append(i);
            result.append(" ");
        }
        return result.toString();
    }

}
