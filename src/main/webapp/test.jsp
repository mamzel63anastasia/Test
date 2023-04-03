<%@ page import="utils.Utils" %>
<%@ page import="dao.GetTestDao" %>
<%@ page import="dao.TestDao" %>
<%@ page import="dao.UserDao" %>
<%@ page import="models.GetTest" %>
<%@ page import="models.Material" %>
<%@ page import="models.Test" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }
    GetTestDao getTestDao = new GetTestDao(session);
    TestDao testDao = new TestDao(session);

    String param = request.getParameter("param");
%>
<html>
<head>
    <title>Назначенный тесты</title>
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
            <a class="nav-link px-3" href="#">Выход</a>
        </div>
    </div>
</header>
<div class="container-fluid">
    <div class="row">
        <%@include file="WEB-INF/include/menuuser.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <%
                if (param != null && param.equals("view")) {
                    int testlId = Utils.checkId(request.getParameter("id"));
                    if (testlId > 0) {
                        Test testView = testDao.itemByUser(testlId);
            %>
            <div class="row">
                <div class="col-12">
                    <b>Название теста:</b><%=testView.getName()%><Br>
                    <b>Проходной балл:</b> <%=testView.getBall()%><br><br>
                    <b>Описание теста:</b><%=testView.getDescription()%>
                    <hr>
                </div>
            </div>
            <%}%>
            <%}%>
            <% if (param == null) {%>
            <div>
                <%for (GetTest item :  getTestDao.allByUser()) {
                    Test test = testDao.itemByUser(item.getId_test());
                %>
                <div class="col-4">
                  <div class="card">
                      <h5 class="card-header"><%=test.getName()%></h5><br>
                      <div class="card-body">
                          <b>Описание теста:</b><%=test.getBall()%><br>
                      </div>
                      <div class="card-footer">
                          <a href="test-start.jsp?id=<%=test.getId()%>" class="btn btn-link">Начать тестирование</a>
                      </div>
                      <div class="card-footer">
                          <a href="test.jsp?param=view&id=<%=test.getId()%>" class="btn btn-link">Прочитать описание теста</a>
                      </div>
                  </div>
                 <%}%>
            </div>
            <%}%>
        </main>
    </div>
</div>
<%@include file="WEB-INF/include/footer.jsp" %>
</body>
</html>