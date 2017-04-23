<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>header</title>
</head>
<link rel="stylesheet" type="text/css" href="../../resources/stylies/header.css" />
<body>
    <table border="0" align="center" width="1020px" style="margin-top: 0;">
        <tr>
            <td width="250px" align="center" height="108">
                <img src="../../resources/images/topMenu/logo.png" width="185" height="64">
            </td>
            <td width="144px">
                <a href="/index" class="button1"></a>
            </td>
            <td width="144px">
                <a href="#" class="button2"></a>
            </td>
            <td width="144px">
                <a href="#" class="button3"></a>
            </td>
            <td width="144px">
                <a href="#" class="button4"></a>
            </td>


            <c:if test="${loggedin == null}">
                <td width="194px" align="center">
                    <a href="/login" class="login"></a>
                </td>
            </c:if>

            <c:if test="${loggedin != null}">
                <td width="194px" align="center">
                    <a href="/logout" class="logout"></a>
                </td>
            </c:if>





        </tr>
    </table>
    <div align="center" style="margin-top: 0"><img src="../../resources/images/topMenu/line.png" width="1020" height="1"></div>
</body>
</html>
