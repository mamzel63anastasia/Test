<%@ page import="utils.Utils" %>
<%@ page import="dao.MaterialDao" %>
<%@ page import="models.Material" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
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
            <div class="row test-builder">
                <input type="hidden" value="" id="test_id">
                <div class="test-settings">
                    <input type="text" id="name" class="form-control " placeholder="Название теста"
                           autocomplete="off">
                    <input type="number" min="1" max="100" step="1" maxlength="3" id="ball" class="form-control"
                           placeholder="Проходной бал" autocomplete="off">
                    <textarea id="description" class="form-control" placeholder="Описание теста"></textarea>
                </div>
                <hr>
                <div class="questions"></div>
                <div class="row">
                    <div class="col-lg-6">
                        <input type="button" class="btn btn-success save_test" value="Сохранить тест">
                    </div>
                    <div class="col-lg-6 right">
                        <input type="button" class="btn btn-link add_question" value="Добавить Вопрос">
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<%@include file="/WEB-INF/include/footer.jsp" %>
<div class="temp-question">
    <div class="row question" question-id="0">
        <div class="row>">
            <div class="col-lg-12">
                <textarea class="form-control question_text" placeholder="Введите вопрос"></textarea>
            </div>
        </div>
        <div class="answers">
            <div class="answer">
                <div class="input-group mb-3">
                    <div class="input-group-text">
                        <input class="form-check-input mt-0 answer_check" type="checkbox" title="Верный ответ">
                    </div>
                    <input type="text" class="form-control answer_text" aria-label="" placeholder="Вариант ответа">
                    <input type="button" value="X" class="btn btn-danger delete_answer">
                </div>
            </div>
        </div>
        <div class="right">
            <input type="button" class="btn btn-link add_answer" value="Добавить ответ"> |
            <input type="button" class="btn btn-link delete_question" value="Удалить вопрос">
        </div>
    </div>
</div>
<script src="/js/test-builder.js"></script>
</body>
</html>