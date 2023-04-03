<%@ page import="utils.Utils" %>
<%@ page import="dao.GetTestDao" %>
<%@ page import="dao.TestDao" %>
<%@ page import="models.data.TestData" %>
<%@ page import="models.Test" %>
<%@ page import="models.GetTest" %>
<%@ page import="models.data.QuestionData" %>
<%@ page import="models.data.AnswerData" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if (!Utils.checkAuthUser(session)) {
        response.sendRedirect("/login.jsp");
        return;
    }

    int id = Utils.checkId(request.getParameter("id"));
    if (id == 0) {
        response.sendRedirect("/test.jsp");
        return;
    }

    TestDao testDao = new TestDao(session);
    TestData testData = testDao.userTestBuilder(id);

%>
<html>
<head>
    <title>Тестирование</title>
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
            <div class="row test-start">
                <input type="hidden" value="<%=id%>" id="test_id">
                <div class="test-settings">
                    <b>Название теста:</b><%=testData.getName()%>
                    <b>Проходной балл:</b> <%=testData.getBall()%><br><br>
                    <b>Описание теста:</b><%=testData.getDescription()%>
                </div>
                <hr>
                <div class="questionData">
                    <% if (testData != null) {
                        for (QuestionData itemQuestion : testData.getQuestions()) {
                    %>
                    <div class="row questionData" questionData-id="<%=itemQuestion.getId()%>">
                        <div class="row>">
                            <div class="col-lg-12">
                                <text class="form-control question_text"
                                      placeholder="Вопрос"><%=itemQuestion.getQuestionText()%>
                                </text>
                            </div>
                        </div>
                        <div class="answerData">
                            <%
                                for (AnswerData itemAnswer : itemQuestion.getAnswers()) {
                            %>
                            <div class="answerData">
                                <div class="input-group mb-3">
                                    <div class="input-group-text">
                                        <input class="form-check-input mt-0 answer_check" type="checkbox"
                                               title="Верный ответ"
                                               answer-id="<%=itemAnswer.getId()%>">
                                    </div>
                                    <%=itemAnswer.getAnswerText()%>
                                </div>
                            </div>
                            <%}%>
                        </div>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>
                <div class="card-footer">
                    <a class="btn btn-link">Закончить тестирование</a>
                </div>
            </div>
        </main>
    </div>
</div>
<%@include file="WEB-INF/include/footer.jsp" %>
</body>
</html>