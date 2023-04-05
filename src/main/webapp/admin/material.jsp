<%@ page import="utils.Utils" %>
<%@ page import="dao.MaterialDao" %>
<%@ page import="models.Material" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }
    MaterialDao materialDao = new MaterialDao(session);

    String param = request.getParameter("param");
%>
<html>
<head>
    <title>Создание учебного материала</title>
    <%@include file="/WEB-INF/include/header.jsp" %>
</head>
<body>
<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6" href="/">АИС Тестирования</a>
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
        <%@include file="/WEB-INF/include/menuadmin.jsp" %>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="row">
                <a class="btn btn-primary" href="/admin/material.jsp?param=add">Добавить материал</a>
            </div>
            <% if (param != null && (param.equals("edit") || param.equals("add"))) {%>
            <div class="row">
                <%@include file="/WEB-INF/forms/material.jsp"%>
            </div>
            <%}%>

            <%
                if (param != null && param.equals("view")) {
                int materialId = Utils.checkId(request.getParameter("id"));
                if (materialId > 0) {
                    Material materialView = materialDao.item(materialId);
            %>
                <div class="row">
                    <div class="col-12">
                        <h1><%=materialView.getName()%></h1>
                        <%=materialView.getText()%>
                    </div>
                </div>
                <%}%>
            <%}%>
            <%
                if (param == null) {
            %>
            <div class="row">
                <% for (Material item : materialDao.all()) {%>
                <div class="col-4">
                    <div class="card">
                        <h5 class="card-header"><%=item.getName()%></h5>
                        <div class="card-body">
                            <%
                                String str = Utils.htmlToString(item.getText());
                            %>
                            <%=str.length() > 100 ? str.substring(0, 100) + "..." : str%>
                        </div>
                        <div class="card-footer">
                            <a href="/admin/material.jsp?param=view&id=<%=item.getId()%>" class="btn btn-link">Открыть</a> |
                            <a href="/admin/material.jsp?param=edit&id=<%=item.getId()%>" class="btn btn-link">Редактировать</a> |
                            <a href="/admin/material?param=delete&id=<%=item.getId()%>" class="btn btn-link">Удалить</a>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
            <%}%>
        </main>
    </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
<%@include file="/WEB-INF/include/editor.jsp"%>
</body>
</html>