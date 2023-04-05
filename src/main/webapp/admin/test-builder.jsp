<%@ page import="dao.TestDao" %>
<%@ page import="models.Test" %>
<%@ page import="utils.Utils" %>
<%@ page import="dao.QuestionDao" %>
<%@ page import="dao.AnswerDao" %>
<%@ page import="models.Question" %>
<%@ page import="models.Answer" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }
    TestDao testDao = new TestDao(session);
    QuestionDao questionDao = new QuestionDao(session);
    AnswerDao answerDao = new AnswerDao(session);

    String param = request.getParameter("param");
    Test test = null;
    if (param != null && param.equals("edit")) {
        int idTest = Utils.checkId(request.getParameter("id"));
        if (idTest > 0) test = testDao.item(idTest);
    }

%>
<html>
<head>
    <title>Создание тестов</title>
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
            <div class="row test-builder">
                <input type="hidden" value="<%=test != null ? test.getId() : 0%>" id="test_id">
                <div class="test-settings">
                    <input type="text" id="name" class="form-control " placeholder="Название теста"
                           autocomplete="off" value="<%=test != null ? test.getName() : ""%>">
                    <input type="number" min="1" max="100" step="1" maxlength="3" id="ball" class="form-control"
                           placeholder="Проходной бал" autocomplete="off"
                           value="<%=test != null ? test.getBall() : ""%>">
                    <textarea id="description" class="form-control"
                              placeholder="Описание теста"><%=test != null ? test.getDescription() : ""%></textarea>
                </div>
                <hr>
                <div class="questions">
                    <% if (test != null) {
                        for (Question itemQuestion : questionDao.all(test.getId())) {
                    %>
                    <div class="row question" question-id="<%=itemQuestion.getId()%>">
                        <div class="row>">
                            <div class="col-lg-12">
                                <textarea class="form-control question_text" placeholder="Введите вопрос"><%=itemQuestion.getTxt()%></textarea>
                            </div>
                        </div>
                        <div class="answers">
                            <%
                                for (Answer itemAnswer : answerDao.all(itemQuestion.getId())) {
                            %>
                            <div class="answer">
                                <div class="input-group mb-3">
                                    <div class="input-group-text">
                                        <input class="form-check-input mt-0 answer_check" type="checkbox"
                                               title="Верный ответ" <%=itemAnswer.getCorrect() == 1 ? "checked" : ""%>>
                                    </div>
                                    <input type="text" class="form-control answer_text" aria-label=""
                                           placeholder="Вариант ответа" value="<%=itemAnswer.getTxt()%>">
                                    <input type="button" value="X" class="btn btn-danger delete_answer">
                                </div>
                            </div>
                            <%}%>
                        </div>
                        <div class="right">
                            <input type="button" class="btn btn-link add_answer" value="Добавить ответ"> |
                            <input type="button" class="btn btn-link delete_question" value="Удалить вопрос">
                        </div>
                    </div>

                    <%}%>
                    <%}%>
                </div>
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