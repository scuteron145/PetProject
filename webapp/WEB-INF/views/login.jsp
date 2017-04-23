<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" type="text/css" href="../../resources/stylies/index.css" />
<link rel="stylesheet" type="text/css" href="../../resources/stylies/login.css" />
<script src="../../resources/scripts/scripts.js"></script>
<body id="main">
<div>
    <jsp:include page="header.jsp"/>
    <div  id="loginImg"><img src="../../resources/images/loginPage/logIn.png" width="131" height="40"></div>

    <form action="${pageContext.request.contextPath}/login" method="POST" id="form">


    <div id="login">
        <input type="text" name="login" value="" size="25" id="inputLogin"/>
    </div>
    <div id="password">
        <input type="password" name="password" value="" size="25" id="inputPassword"/>
    </div>
    <div  id="error">
        <c:if test="${usererror != null}">
               <h4 style="color: #ffffff; text-align: left;"> ${usererror}</h4>
        </c:if>
    </div>
    <div id="signIn">
        <a href="#" onclick="document.getElementById('form').submit()" class="signMeIn"></a>
        <a href="/registration" class="newAccount"></a>
    </div>

    </form>
</div>
<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>