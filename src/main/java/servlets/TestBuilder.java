package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.Question;
import models.QuestionData;
import models.Test;
import utils.ResponseData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = {"/admin/testBuilder"})
public class TestBuilder extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ResponseData responseData = new ResponseData();

        JsonReader jsonReader =  new JsonReader(req.getReader());

        QuestionData data = gson.fromJson(jsonReader, QuestionData.class);

        Test test = new Test(
                data.getName(),
                data.getDescription(),
                data.getBall()
        );



//        responseData.setMessage("Сообщение с ошибкой"); какое сообщение показать если все плохо. Тогда редиректа не будет
//        responseData.setLocation("/admin/test-builder.jsp"); куда редиректить если все ок

        resp.getWriter().print(responseData);
    }
}