<%@ page import="models.Material" %>
<%@ page import="dao.MaterialDao" %>
<%
    Material material = null;
    if (request.getParameter("param").equals("edit")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            material = materialDao.item(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
%>
<form method="post" action="/admin/material" class="form">
    <%if (material != null) {%>
    <input type="hidden" value="<%=material.getId()%>" name="id">
    <%}%>
    <input
            type="text"
            name="name"
            placeholder="Укажите название материала"
            class="form-control"
            value="<%=material != null ? material.getName() : ""%>"
    >
    <textarea class="form-control" name="text" id="editor"><%=material != null ? material.getText() : ""%></textarea>
    <input type="submit" value="Сохранить" class="btn btn-success">
</form>