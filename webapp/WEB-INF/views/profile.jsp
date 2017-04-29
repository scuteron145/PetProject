<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="$../../resources/scripts/scripts.js"></script>
</head>
<link rel="stylesheet" type="text/css" href="../../resources/stylies/index.css" />
<body id="main">
<div>
    <jsp:include page="header.jsp"/>
</div>

<c:if test="${edit == null}">
    <table border="0" align="center">
        <tr>
            <td><h3 style="color: #ffffff;"> Login:</h3></td>
            <td><h3 style="color: #ffffff;"> ${login}</h3></td>
        </tr>
        <tr>
            <td><h3 style="color: #ffffff;"> E-mail:</h3></td>
            <td><h3 style="color: #ffffff;"> ${email}</h3></td>
        </tr>
        <tr>
            <td><h3 style="color: #ffffff;"> Sex:</h3></td>
            <td><h3 style="color: #ffffff;"> ${sex}</h3></td>
        </tr>
        <tr>
            <td><h3 style="color: #ffffff;"> Age:</h3></td>
            <td><h3 style="color: #ffffff;"> ${age}</h3></td>
        </tr>
        <tr>
            <td><h3 style="color: #ffffff;"> Comment:</h3></td>
            <td><h3 style="color: #ffffff;"> ${comment}</h3></td>
        </tr>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/profile?edit=true" method="POST">
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <c:if test="${usererror != null}">
                <td><h4 style="color: #ffffff; text-align: center; margin-top: 25px;"> ${usererror}</h4></td>
            </c:if>
        </tr>
    </table>


</c:if>


<c:if test="${edit == true}">
    <form action="${pageContext.request.contextPath}/profile?edit=changes" method="POST">

        <table border="0"  align="center">
            <tr>
                <td><h3 style="color: #ffffff;"> Login:</h3></td>
                <td><h3 style="color: #ffffff;"> ${login}</h3></td>
                <td><h3 style="color: #ffffff;"> Enter new data. If you do not want to change any field, leave it blank. </h3></td>
            </tr>
            <tr>
                <td><h3 style="color: #ffffff;"> E-mail:</h3></td>
                <td><h3 style="color: #ffffff;"> ${email}</h3></td>
                <td><input type="text" name="email" value="" size="30"/></td>
            </tr>
            <tr>
                <td><h3 style="color: #ffffff;"> Sex:</h3></td>
                <td><h3 style="color: #ffffff;"> ${sex}</h3></td>
                <td>
                    <SELECT size="1" name="sex">
                        <OPTION value="male">
                            Male
                        </OPTION>
                        <OPTION value="female">
                            Female
                        </OPTION>
                    </SELECT>
                </td>
            </tr>
            <tr>
                <td><h3 style="color: #ffffff;"> Age:</h3></td>
                <td><h3 style="color: #ffffff;"> ${age}</h3></td>
                <td><input type="text" name="age" value="" size="30"/></td>
            </tr>
            <tr>
                <td><h3 style="color: #ffffff;"> Comment:</h3></td>
                <td><h3 style="color: #ffffff;"> ${comment}</h3></td>
                <td><textarea name="comment" cols="76" rows="10"></textarea></td>
            </tr>
            <tr>
                <td><input type="submit" value="OK"/></td>
            </tr>
        </table>
    </form>
</c:if>



</body>
</html>