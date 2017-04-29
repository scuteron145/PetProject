package com.project.socket;

import com.project.beans.Message;
import com.project.beans.User;
import com.project.beans.XmlSocketBox;
import com.project.chat.MessagesDeque;
import com.project.chat.OnlineUsersMap;
import org.w3c.dom.Document;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat")
public class ChatSocketManager {

    @OnOpen
    public void open(Session session) {
        String login = String.valueOf(session.getRequestParameterMap().get("login").get(0));
        OnlineUsersMap.onlineUserMap.get(login).setSession(session);
        refreshMessages();
    }

    @OnClose
    public void close(Session session) {

    }

    @OnError
    public void onError(Throwable error){

    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        Document doc = XmlSocketBox.instanse.parseXmlFromString(message);
        if (!doc.getElementsByTagName("message").item(0).getAttributes().getNamedItem("textofthemessage").getNodeValue().equals("")) {
            messagesHandler(session, doc);
        }
    }

    private void messagesHandler(Session session, Document doc) {
        String textOfTheMessage = doc.getElementsByTagName("message").item(0).getAttributes().getNamedItem("textofthemessage").getNodeValue();
        MessagesDeque.messagesDeque.addFirst(new Message(String.valueOf(session.getRequestParameterMap().get("login").get(0)), textOfTheMessage));
        refreshMessages();
    }


    private void refreshMessages() {
        for (String i : OnlineUsersMap.onlineUserMap.keySet()) {
            User temp = OnlineUsersMap.onlineUserMap.get(i);
            try {
                temp.getSession().getBasicRemote().sendText(MessagesDeque.dequeToXmlString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
