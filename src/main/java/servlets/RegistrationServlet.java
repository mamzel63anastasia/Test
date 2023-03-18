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

@WebServlet(urlPatterns = {"/userReg"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String fio = request.getParameter("fio");
        String mail = request.getParameter("mail");
        String tabNumber = request.getParameter("tabNumber");
        String department = request.getParameter("department");
        String login = request.getParameter("login");
        String password = Utils.buildHash(request.getParameter("password"));
        int idRole = Integer.parseInt(request.getParameter("role"));

        User user = new User(login, password, fio, mail, tabNumber, department, idRole);

        try {
            UserDao userDao = new UserDao();

            if (userDao.item(login) == null) {
              userDao.add(user);
            } else {
                session.setAttribute("message", "Такой пользователь уже существует");
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            session.setAttribute("message", "ошибка на стороне сервера: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("/login.jsp");
    }

}
