package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.User;
import models.data.TestData;
import utils.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/testStart"})
public class TestStartServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader = new JsonReader(req.getReader());

        TestData data = gson.fromJson(jsonReader, TestData.class);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");


    }
}
