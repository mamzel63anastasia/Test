<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 14.03.2023
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация пользователя</title>
    <%@include file="WEB-INF/include/header.jsp"%>
</head>
<body class="text-center">
<style>
    .form-signin {
        max-width: 400px;
        padding: 15px;
    }
    .form-floating {
        margin-bottom: 7px;
    }
    .alert {
        margin-top: 10px;
    }
    .btn {
        width: 100%;
        margin-bottom: 10px;
    }
</style>
<main class="form-signin w-100 m-auto">
    <form method="post" action="/userReg">
        <h1>Регистрация</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingFio" placeholder="ФИО" name="fio">
            <label for="floatingFio">Введите ФИО</label>
        </div>

        <div class="form-floating">
            <input type="email" class="form-control" id="floatingMail" placeholder="Email" name="mail">
            <label for="floatingMail">Введите email</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingTab" placeholder="Табельный номер" name="tabNumber">
            <label for="floatingTab">Введите табельный номер</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingDep" placeholder="Отделение" name="department">
            <label for="floatingDep">Введите отделение</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingUser" placeholder="Логин" name="login">
            <label for="floatingUser">Введите логин</label>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPass" placeholder="Пароль" name="password">
            <label for="floatingPass">Введите пароль</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Регистрация</button>
    </form>
</main>
<%@include file="WEB-INF/include/footer.jsp"%>
</body>
</html>
