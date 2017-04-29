<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="$../../resources/scripts/scripts.js"></script>
<c:if test="${loggedin != null}">
    <script>
      var socket = new WebSocket('ws://' + window.location.host + '/chat?login=${login}');

      socket.onopen = function (event) {

      };
      socket.onclose = function () {

      };
      socket.onmessage = function (event) {
        var   xmlDoc, message, value;
        message = event.data;

        xmlDoc = parseXmlFromString(message);

        if(xmlDoc.getElementsByTagName("message").item(0).attributes.getNamedItem("textofthemessage").nodeValue != ""){
          messagesDequeHandler(xmlDoc);
        }
      };

    </script>
</c:if>
  </head>
  <link rel="stylesheet" type="text/css" href="../../resources/stylies/index.css" />
  <body id="main">
  <div>
    <jsp:include page="header.jsp"/>
  </div>

  <c:if test="${loggedin == null}">
    <h4 align="center" style="color: #ffffff;">You can not participate in the discussions without logging in. Please login.</h4>
    <p style="text-align: center"><img src="$../../resources/images/indexPage/notLoggedIn.png" width="256" height="256" alt="Need login."></p>
  </c:if>

  <c:if test="${loggedin != null}">
    <div id="inputButton">
      <button type="submit" onclick="socket.send(readUsersInput());">
        <img src="$../../resources/images/indexPage/send.png" width="56" height="16" style="margin: 0px 0px 0px 0px" alt="Send">
      </button>
    </div>

    <div id="inputMessage">
      <input type="text" name="login" value="" size="100" id="inputtext"/>
    </div>

    <div id="allUsersChat"> </div>
  </c:if>

  </body>
</html>
