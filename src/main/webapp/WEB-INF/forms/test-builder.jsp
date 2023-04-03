<%@ page import="models.Question" %>
<%@ page import="models.Test" %>

<%
    Test test = null;
    if (request.getParameter("param").equals("edit")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            test = testDao.item(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
%>
<%
    Question questionData = null;
    if (request.getParameter("param").equals("edit")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            questionData = questionDao.item(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
%>
<form method="post" action="/admin/test-builder" class="form">
    <%if (test != null) {%>
    <input type="hidden" value="<%=test.getId()%>" name="id">
    <%}%>
    <input
            type="text"
            name="name"
            placeholder="Название теста"
            class="form-control"
            value="<%=test != null ? test.getName() : ""%>"
    >
    <input
            type="number"
            min="1" max="100" step="1" maxlength="3" id="ball"
            placeholder="Проходной бал"
            class="form-control"
            autocomplete="off"
            value="<%=test != null ? test.getBall() : ""%>"
    >
    <textarea class="form-control"  id="description"  placeholder="Описание теста"><%=test != null ? test.getDescription() : ""%></textarea>
    <input type="submit" value="Сохранить" class="btn btn-success">
</form>