<%@ page import="models.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.03.2023
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<User> users = new ArrayList<>();

%>
<html>
<head>
    <title>Пользователи</title>
    <%@include file="WEB-INF/include/header.jsp"%>
</head>
<body>
<table>
    <tr>
        <th>ФИО</th>
        <th>Email</th>
        <th>Табельный номер</th>
        <th>Отделение</th>
        <th>Логин</th>
        <th>Пароль</th>
    </tr>
    <%for (User item : users){%>
    <tr>
        <td>
            <%=item.getFio()%>
        </td>
        <td>
            <%=item.getMail()%>
        </td>
        <td>
            <%=item.getTabNumber()%>
        </td>
        <td>
            <%=item.getDepartment()%>
        </td>
        <td>
            <%=item.getLogin()%>
        </td>
        <td>
            <%=item.getPassword()%>
        </td>
    </tr>
    <%}%>
</table>

<%@include file="WEB-INF/include/footer.jsp"%>
</body>
</html>
