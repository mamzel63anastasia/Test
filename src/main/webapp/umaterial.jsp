<%@ page import="utils.Utils" %>
<%@ page import="dao.GetTestDao" %>
<%@ page import="dao.MaterialDao" %>
<%@ page import="models.GetTest" %>
<%@ page import="models.Material" %>
<%@ page import="dao.UserDao" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }

    GetTestDao getTestDao = new GetTestDao(session);
    MaterialDao materialDao = new MaterialDao(session);


    String param = request.getParameter("param");
%>
<html>
<head>
    <title>Назначенные учебные материалы</title>
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
                    int materialId = Utils.checkId(request.getParameter("id"));
                    if (materialId > 0) {
                        Material materialView = materialDao.itemByUser(materialId);
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
            <%for (GetTest items : getTestDao.allByUser()){
            Material material = materialDao.itemByUser(items.getId_material());
            %>
            <div class="col-4">
                <div class="card">
                    <h5 class="card-header"><%=material.getName()%></h5>
                    <div class="card-body">
                        <%
                          String str = Utils.htmlToString(material.getText());
                        %>
                        <%=str.length() > 100 ? str.substring(0, 100) + "..." : str%>
                    </div>
                    <div class="card-footer">
                        <a href="umaterial.jsp?param=view&id=<%=material.getId()%>" class="btn btn-link">Открыть</a>
                    </div>
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