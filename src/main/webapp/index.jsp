<%@ page import="dao.Connector" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    try {
        Connector connector = new Connector();
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
%>
<html>
<head>
    <title>Главная</title>
    <%@include file="WEB-INF/include/header.jsp" %>
</head>
<body>
<h2>Hello World!</h2>
<%@include file="WEB-INF/include/footer.jsp"%>
</body>
</html>
