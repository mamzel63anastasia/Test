package servlets;

import dao.UserDao;
import models.User;
import utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = Utils.buildHash(request.getParameter("password"));

        try {
            UserDao userDao = new UserDao();
            User user = userDao.item(login, password);

            if (user != null) {
                session.setAttribute("auth", user);
                switch (user.getRole()) {
                    case 1:
                        response.sendRedirect("/");
                        return;
                    case 2:
                        response.sendRedirect("/admin");
                        return;
                }
            } else {
                session.setAttribute("message", "Не удалось найти пользователя");
                response.sendRedirect("/login.jsp");
                return;
            }

        } catch (SQLException | ClassNotFoundException e) {
            session.setAttribute("message", "ошибка на стороне сервера: " + e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect("/login.jsp");

    }
}
