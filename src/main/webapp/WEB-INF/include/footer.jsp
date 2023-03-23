<%
    String message = (String) session.getAttribute("message");
    session.removeAttribute("message");
%>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery-3.6.3.min.js"></script>
<script src="/js/jquery.serializeObject.js"></script>
<script>
    function addMessage(message) {
        let block = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
            message +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
        $("main").append(block);
    }
<%=message != null ? "addMessage('"+ message +"')" : "" %>
</script>