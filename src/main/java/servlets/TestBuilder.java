package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.AnswerDao;
import dao.QuestionDao;
import dao.TestDao;
import models.*;
import utils.ResponseData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        JsonReader jsonReader = new JsonReader(req.getReader());

        QuestionData data = gson.fromJson(jsonReader, QuestionData.class);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");

        TestDao testDao = new TestDao(session);
        int idTest = testDao.add(new Test(
                data.getName(),
                data.getDescription(),
                data.getBall(),
                user.getId()
        ));

        if (idTest > 0) {
            QuestionDao questionDao = new QuestionDao(session);
            AnswerDao answerDao = new AnswerDao(session);
            if (data.getQuestions().size() == 0) {
                testDao.delete(idTest);
            } else {
                for (QuestionData.Question question : data.getQuestions()) {
                    int idQuestion = questionDao.add(new Question(
                            question.getQuestionText(),
                            idTest
                    ));

                    if (idQuestion > 0 &&  question.getAnswers().size() > 0) {
                        for (QuestionData.Answer answer : question.getAnswers()) {
                            answerDao.add(new Answer(
                                    answer.getAnswerText(),
                                    answer.isAnswerCheck() ? 1 : 0,
                                    idQuestion
                            ));

                        }

                    } else {
                        questionDao.delete(idQuestion);
                        testDao.delete(idTest);
                        break;
                    }
                }
            }
        }

//        responseData.setMessage("Сообщение с ошибкой"); какое сообщение показать если все плохо. Тогда редиректа не будет
        responseData.setLocation("/admin/test-builder.jsp"); //куда редиректить если все ок

        resp.getWriter().print(responseData);
    }
}