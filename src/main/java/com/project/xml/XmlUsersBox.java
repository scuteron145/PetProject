package com.project.xml;

import com.project.beans.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlUsersBox {
    public static XmlUsersBox instanse = new XmlUsersBox();

    public Document createUsersBox(Document doc) {
        Element usersbox = doc.createElement("usersbox");
        doc.appendChild(usersbox);
        return doc;
    }

    public Document appendUser(Document doc) {
        Element user = doc.createElement("user");
        user.setAttribute("login", null);
        user.setAttribute("password", null);
        user.setAttribute("email", null);
        user.setAttribute("sex", null);
        user.setAttribute("age", null);
        user.setAttribute("comment", null);
        doc.getElementsByTagName("usersbox").item(0).appendChild(user);
        return doc;
    }

    public Document setUser(Document doc, User user, int index) {
        doc.getElementsByTagName("user").item(index).getAttributes().getNamedItem("login").setTextContent(user.getLogin());
        doc.getElementsByTagName("user").item(index).getAttributes().getNamedItem("password").setTextContent(user.getPassword());
        doc.getElementsByTagName("user").item(index).getAttributes().getNamedItem("email").setTextContent(user.getEmail());
        doc.getElementsByTagName("user").item(index).getAttributes().getNamedItem("sex").setTextContent(user.getSex());
        doc.getElementsByTagName("user").item(index).getAttributes().getNamedItem("age").setTextContent(String.valueOf(user.getAge()));
        doc.getElementsByTagName("user").item(index).getAttributes().getNamedItem("comment").setTextContent(user.getComment());
        return doc;
    }

}