package servlets;

import dao.Connector;
import dao.UserDao;
import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = {"/userReg"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //redirect /registration.jsp
        response.sendRedirect("/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fio = request.getParameter("fio");
        String mail = request.getParameter("mail");
        String tabNumber = request.getParameter("tabNumber");
        String department = request.getParameter("department");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password, fio, mail, tabNumber, department);

        try {
            UserDao userDao = new UserDao();

            if (userDao.fainedUserByLoginOrNull(login) == null) {
              userDao.addUser(user);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/login.jsp");
    }

}
