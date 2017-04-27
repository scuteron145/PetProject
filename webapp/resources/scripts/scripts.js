/*
 <socketbox>
 <message login="test" time="15:36:37" textofthemessage="sometext">    </message>
 </socketbox>
 */

function resetInput(id) {
    document.getElementById(id).value = "";
}

function setInputText(id, text) {
    document.getElementById(id).value = text;
}

function readUsersInput(login) {
    var massage = document.getElementById("inputtext").value;
    document.getElementById("inputtext").value = "";
    var socketBox = createSocketBox();
    socketBox = setMessage(socketBox, login, massage);
    var result = generateStringFromXml(socketBox);
    return result;
}



function messagesDequeHandler(doc) {
    var i;
    var strResult = "";
    for (i = 0; i < doc.getElementsByTagName("message").length; i++) {
        strResult = strResult + '<i style="color: #10ff3d;">' + doc.getElementsByTagName("message").
            item(i).attributes.getNamedItem("time").nodeValue + ' " ' + doc.getElementsByTagName("message").
            item(i).attributes.getNamedItem("login").nodeValue + ' " </i><b style="color: #ffffff;">' +
            htmlStringSplitter(doc.getElementsByTagName("message").item(i).attributes.getNamedItem("textofthemessage").
                nodeValue) + '</b></br>';
    }
    document.getElementById('allUsersChat').innerHTML = strResult;
}



function generateLogoutMessage(login) {
    var socketBox = createSocketBox();
    socketBox = setMessage(socketBox, login, "leaved us.");
    socketBox.getElementsByTagName("message").item(0).attributes.getNamedItem("logout").textContent = "true"
    var result = generateStringFromXml(socketBox);
    return result;
}

function createSocketBox() {
    var doc = document.implementation.createDocument('http://www.w3.org/1999/xhtml', 'html', null);
    var socketBox = document.createElementNS('http://www.w3.org/1999/xhtml', 'socketbox');
    doc.documentElement.appendChild(socketBox);
    var message = document.createElementNS('http://www.w3.org/1999/xhtml', 'message');
    message.setAttribute('login', "");
    message.setAttribute('time', "");
    message.setAttribute('textofthemessage', "");
    socketBox.appendChild(message);
    return doc;
}

function setMessage(doc, login, message) {
    doc.getElementsByTagName("message").item(0).attributes.getNamedItem("time").textContent = createTime();
    doc.getElementsByTagName("message").item(0).attributes.getNamedItem("login").textContent = login;
    doc.getElementsByTagName("message").item(0).attributes.getNamedItem("textofthemessage").textContent = message;

    return doc;
}


function createTime() {
    var time = new Date().getTime();
    var hours = (time / 3600000) % 24 + 2; //+2 for Ukraine
    var minutes = (time % 3600000) / 60000;
    var seconds = ((time % 3600000) % 60000) / 1000;
    var result = "";
    if (hours < 10) {
        result = result + "0" + ~~hours;
    } else {
        result = result + ~~hours;
    }
    result = result + ':';
    if (minutes < 10) {
        result = result + "0" + ~~minutes;
    } else {
        result = result + ~~minutes;
    }
    result = result + ':';
    if (seconds < 10) {
        result = result + "0" + ~~seconds;
    } else {
        result = result + ~~seconds;
    }
    return result;
}

function generateStringFromXml(doc) {
    var serializer = new XMLSerializer();
    var xmlString = serializer.serializeToString(doc);
    return xmlString;
}

function parseXmlFromString(message) {
    var parser, xmlDoc;
    parser = new DOMParser();
    xmlDoc = parser.parseFromString(message, "text/xml");
    return xmlDoc;
}

function htmlStringSplitter(text) {
    if (text.length > 100) {
        var result = "</br>";
        var stop = false;
        var start = 0;
        var end = 100;
        while (!stop) {
            if (end < text.length) {
                result = result + text.substring(start, end) + "</br>";
                start = end;
                end += 100;
            } else {
                result = result + text.substring(start, text.length - 1);
                stop = true;
            }
        }
        return result;
    } else {
        return text;
    }
}

function end() {
    socket.close();
    window.close();
}

$('html').keydown(function (eventObject) { //отлавливаем нажатие клавиш
    if (event.keyCode == 13) { //если нажали Enter, то true
        socket.send(readUsersInput());
    }
});


