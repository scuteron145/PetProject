package com.project.xml;

import com.project.beans.Message;
import org.w3c.dom.*;

public class XmlSocketBox {

    public static XmlSocketBox instanse = new XmlSocketBox();

    public Document createSocketBox(Document doc) {
        Element socketBox = doc.createElement("socketbox");
        doc.appendChild(socketBox);
        doc = appendMessage(doc);
        return doc;
    }

    public Document createSocketBoxForManyMessages(Document doc) {
        Element socketBox = doc.createElement("socketbox");
        doc.appendChild(socketBox);
        return doc;
    }

    public Document appendMessage(Document doc) {
        Element message = doc.createElement("message");
        message.setAttribute("login", null);
        message.setAttribute("time", null);
        message.setAttribute("textofthemessage", null);
        doc.getElementsByTagName("socketbox").item(0).appendChild(message);
        return doc;
    }

    public Document setMessage(Document doc, Message message) {
        doc = setMessage(doc, message, 0);
        return doc;
    }

    public Document setMessage(Document doc, Message message, int index) {
        doc.getElementsByTagName("message").item(index).getAttributes().getNamedItem("login").setTextContent(message.getChatterNick());
        doc.getElementsByTagName("message").item(index).getAttributes().getNamedItem("time").setTextContent(message.getTime());
        doc.getElementsByTagName("message").item(index).getAttributes().getNamedItem("textofthemessage").setTextContent(message.getMessage());
        return doc;
    }



}

