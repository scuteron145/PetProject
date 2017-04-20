<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
</head>
<link rel="stylesheet" type="text/css" href="../../resources/stylies/index.css" />
<script src="../../resources/scripts/scripts.js"></script>
<body>
<div id="main">
    <jsp:include page="header.jsp"/>

        <div style="width: 75%; margin-left: 25%">
            <form action="${pageContext.request.contextPath}/registration" method="POST">
                <table border="0">

                    <c:if test="${wrongLoginOrPassword != null}">
                        <tr>
                            <td><h4 style="color: #ffffff; text-align: center; margin-top: 25px;"> ${wrongLoginOrPassword}</h4></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td><h2 style="color: #ffffff; text-align: center; margin-top: 25px;">Registration</h2></td>
                    </tr>
                </table>
                <table border="0" style="margin-top: 10px">
                    <tr>
                        <td> <b style="color: #ffffff;">*Input login: </b></td>
                        <td><input type="text" name="login" value="" size="10"/></td>
                    </tr>

                    <tr>
                        <td> <b style="color: #ffffff;">*Input password: </b></td>
                        <td><input type="password" name="password" value="" size="10"/></td>
                    </tr>

                    <tr>
                        <td><b style="color: #ffffff;">*Input your email: </b></td>
                        <td><input type="text" name="email" value="" size="10"/></td>
                    </tr>

                    <tr>
                        <td><b style="color: #ffffff;">*Choose: </b></td>
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
                        <td><b style="color: #ffffff;">Input your age: </b></td>
                        <td><input type="text" name="age" value="" size="10"/></td>
                    </tr>
                </table>

                <table border="0">
                    <tr>
                        <td><b style="color: #ffffff;">You can tell something about yourself: </b></td>
                    </tr>

                    <tr>
                        <td><textarea name="comment" cols="76" rows="10">
    </textarea>
                        </td>
                    </tr>

                    <tr>
                        <td>   </td>
                    </tr>

                </table>
                <input type="submit" value="OK"/>
                </form>


</div>
        <jsp:include page="footer.jsp"/>
</body>
</html>