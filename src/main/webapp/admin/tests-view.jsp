<%@ page import="utils.Utils" %>
<%@ page import="dao.MaterialDao" %>
<%@ page import="models.Material" %>
<%@ page import="dao.TestDao" %>
<%@ page import="models.Test" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  if (!Utils.checkAuthUser(session)) {
    response.sendRedirect("/login.jsp");
    return;
  }
  TestDao testDao = new TestDao(session);
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
      <div class="row">

        <% for (Test item : testDao.all()) {%>
        <div class="col-4">
          <div class="card">
            <h5 class="card-header"><%=item.getName()%></h5>
            <div class="card-body">
              Вывести информацию о тесте
            </div>
            <div class="card-footer">
              <a href="/admin/test-builder.jsp?param=edit&id=<%=item.getId()%>" class="btn btn-link">Редактировать</a> |
              <a href="/admin/test-builder?param=delete&id=<%=item.getId()%>" class="btn btn-link">Удалить</a>
            </div>
          </div>
        </div>
        <%}%>


      </div>
    </main>
  </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
<%@include file="/WEB-INF/include/editor.jsp"%>
</body>
</html>