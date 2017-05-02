<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="$../../resources/scripts/scripts.js"></script>


</head>
<link rel="stylesheet" type="text/css" href="../../resources/stylies/index.css" />
<link rel="stylesheet" type="text/css" href="../../resources/stylies/find.css" />
<body id="main">
<div>
    <jsp:include page="header.jsp"/>
</div>

<div align="center" id="findInput">
    <form action="${pageContext.request.contextPath}/find" method="POST">
        <table border="0">
            <tr>
                <td><b style="color: #ffffff;">Input the nickname of the user you want to find: </b></td>
                <td><input type="text" name="find" value="" size="20"/></td>
                <td><input type="submit" value="find"/></td>
            </tr>
        </table>
    </form>

</div>
<div id="foundusers">

</div>

<script>
    var   xmlDoc, users;
    users = `${foundusers}`;
    xmlDoc = parseXmlFromString(users);
    usersHandler(xmlDoc);
</script>



</body>
</html>
