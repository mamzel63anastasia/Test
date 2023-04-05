<%@ page import="utils.Utils" %>
<%@ page import="dao.StatisticDao" %>
<%@ page import="models.Statistic" %>
<%@ page import="dao.TestDao" %>
<%@ page import="models.Test" %>
<%@ page import="dao.Dao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }

    StatisticDao statisticDao = new StatisticDao(session);
    TestDao testDao = new TestDao(session);
%>
<html>
<head>
    <title>Статистика</title>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>
<body>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/">АИС Тестирование</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse"
            data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-nav">
        <div class="nav-item text-nowrap">
            <a class="nav-link px-3" href="/exit">Выход</a>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="row">
        <%@include file="WEB-INF/include/menuuser.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <table class="table table-striped">
                <tr>
                    <th>Название теста</th>
                    <th>Дата прохождения теста</th>
                    <th>Полученный балл</th>
                </tr>
                <%for (Statistic list : statisticDao.all()) {

                %>
                <tr>
                    <%
                        Test testView = testDao.itemByUser(list.getIdTest());
                    %>
                    <td><%=testView.getName()%></td>
                    <td><%=list.getDate()%></td>
                    <td><%=list.getBall()%></td>
                </tr>
                <%}%>
            </table>
        </main>
    </div>
</div>
<%@include file="WEB-INF/include/footer.jsp" %>
</body>
</html>