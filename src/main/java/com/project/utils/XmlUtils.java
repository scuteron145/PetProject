package com.project.utils;

import com.project.beans.Message;
import com.project.beans.User;
import com.project.xml.XmlSocketBox;
import com.project.xml.XmlUsersBox;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class XmlUtils {
    public static XmlUtils instanse = new XmlUtils();

    public Document newDocument() {
        Document docResult = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        docResult = documentBuilder.newDocument();
        return docResult;
    }

    public String generateStringFromXml(Document doc) {
        OutputFormat format = new OutputFormat(doc);
        StringWriter stringOut = new StringWriter();
        XMLSerializer serial = new XMLSerializer(stringOut, format);
        try {
            serial.serialize(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringOut.toString();
    }

    public Document parseXmlFromString(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(xml);
        ByteArrayInputStream input = null;
        try {
            input = new ByteArrayInputStream(
                    xmlStringBuilder.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = builder.parse(input);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public Document dequeToDocument(ConcurrentLinkedDeque<Message> messagesDeque){
        Document xmlSocketBox = XmlUtils.instanse.newDocument();
        xmlSocketBox = XmlSocketBox.instanse.createSocketBoxForManyMessages(xmlSocketBox);
        int k = 0;
        for (Message i: messagesDeque) {
            XmlSocketBox.instanse.appendMessage(xmlSocketBox);
            XmlSocketBox.instanse.setMessage(xmlSocketBox, i, k);
            k++;
        }
        return xmlSocketBox;
    }

    public Document listOfUsersToDoc(List<User> users){
        Document usersBox = XmlUtils.instanse.newDocument();
        usersBox = XmlUsersBox.instanse.createUsersBox(usersBox);
        int k = 0;
        for (User i: users) {
            XmlUsersBox.instanse.appendUser(usersBox);
            XmlUsersBox.instanse.setUser(usersBox, i, k);
            k++;
        }
        return usersBox;
    }

}
