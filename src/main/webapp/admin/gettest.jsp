<%@ page import="dao.GetTestDao" %>
<%@ page import="dao.MaterialDao" %>
<%@ page import="dao.TestDao" %>
<%@ page import="dao.UserDao" %>
<%@ page import="models.GetTest" %>
<%@ page import="models.Material" %>
<%@ page import="models.Test" %>
<%@ page import="models.User" %>
<%@ page import="utils.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }
    GetTestDao getTestDao = new GetTestDao(session);
    UserDao userDao = new UserDao();
    TestDao testDao = new TestDao(session);
    MaterialDao materialDao = new MaterialDao(session);


%>
<html>
<head>
    <title>главная</title>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>
<body>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/">ИС Тестирования</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="#">Выход</a>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include/menuadmin.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <form method="post" action="/admin/getTest">
                <select name="user" class="form-control">
                    <% for (User list : userDao.all(1)) {%>
                    <option value="<%=list.getId()%>"><%=list.getFio()%>, <%=list.getLogin()%>
                    </option>
                    <%}%>
                </select>
                <select name="test" class="form-control">
                    <% for (Test item : testDao.all()) {%>
                    <option value="<%=item.getId()%>"><%=item.getName()%>
                    </option>
                    <%}%>
                </select>
                <select name="material" class="form-control">
                    <% for (Material item : materialDao.all()) {%>
                    <option value="<%=item.getId()%>"><%=item.getName()%>
                    </option>
                    <%}%>
                </select>
                <div class="row">
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Войти</button>
                </div>
            </form>
            <table class="table table-striped">
                <tr>
                    <th>Фио, логин тестируемого</th>
                    <th>Тест</th>
                    <th>Учебный материал</th>
                </tr>
                <%for (GetTest items : getTestDao.all()) {%>
                <tr>
                    <td><%=userDao.item(items.getId_user()).getFio()%>, <%=userDao.item(items.getId_user()).getLogin()%>
                    </td>
                    <td><%=testDao.item(items.getId()).getName()%>
                    </td>
                    <td><%=testDao.item(items.getId_material()).getName()%></td>
                </tr>
                <%}%>
            </table>

        </main>
</body>
</html>
