<%@ page import="utils.Utils" %>
<%@ page import="models.Material" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(request)) {
        response.sendRedirect("/login.jsp");
        return;
    }
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
            <form method="post" action="/">
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingOne">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            </button>
                        </h2>
                        <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                            <div class="accordion-body">
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
            </form>
        </main>
    </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>