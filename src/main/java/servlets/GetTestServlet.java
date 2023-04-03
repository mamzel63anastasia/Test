package servlets;

import dao.GetTestDao;
import models.GetTest;
import models.User;
import org.jsoup.internal.StringUtil;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/getTest"})
public class GetTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/admin/gettest.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");
        GetTestDao dao = new GetTestDao(session);

        int id_user = Utils.checkId(req.getParameter("user"));
        int id_test = Utils.checkId(req.getParameter("test"));
        int id_material = Utils.checkId(req.getParameter("material"));
        if (id_user > 0 && id_test > 0 && id_material > 0) {
            GetTest getTest = new GetTest(id_user, id_test , id_material, user.getId());
            dao.add(getTest);
        } else {
            session.setAttribute("message", "Недостаточно параметров");
        }
        resp.sendRedirect("/admin/gettest.jsp");
    }


}
