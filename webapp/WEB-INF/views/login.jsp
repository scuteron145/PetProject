<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<link rel="stylesheet" type="text/css" href="../../resources/stylies/index.css" />
<link rel="stylesheet" type="text/css" href="../../resources/stylies/login.css" />
<script src="../../resources/scripts/scripts.js"></script>
<body>
<div id="main">
    <jsp:include page="header.jsp"/>
    <div  id="loginImg"><img src="../../resources/images/loginPage/logIn.png" width="131" height="40"></div>
    <div id="login">
        <input type="text" name="login" value="" size="25" id="inputLogin"/>
    </div>
    <div id="password">
        <input type="text" name="password" value="" size="25" id="inputPassword"/>
    </div>
    <div  id="error"><img src="../../resources/images/loginPage/error.png" width="212" height="14"></div>
    <div id="signIn">
        <a href="/login" class="signMeIn"></a>
        <a href="/registration" class="newAccount"></a>
    </div>
</div>
<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>