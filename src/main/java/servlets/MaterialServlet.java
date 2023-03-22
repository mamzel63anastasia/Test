package servlets;

import dao.MaterialDao;
import models.Material;
import models.User;
import org.jsoup.internal.StringUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/material"})
public class MaterialServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("param") != null && request.getParameter("param").equals("delete")) {
            if (StringUtil.isNumeric(request.getParameter("id"))) {
                int id = Integer.parseInt(request.getParameter("id"));
                MaterialDao dao = new MaterialDao(request.getSession());
                dao.delete(id);
            }
        }
        response.sendRedirect("/admin/material.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String text = request.getParameter("text");
        User user = (User) session.getAttribute("auth");

        MaterialDao dao = new MaterialDao(session);

        if (StringUtil.isNumeric(request.getParameter("id"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (id > 0) {
                Material material = dao.item(id);
                material.setName(name);
                material.setText(text);
                dao.update(material);
            }
        } else {
            Material material = new Material(name, text, user.getId());
            dao.add(material);
        }

        response.sendRedirect("/admin/material.jsp");
    }
}
