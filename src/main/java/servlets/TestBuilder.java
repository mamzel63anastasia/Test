package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.AnswerDao;
import dao.QuestionDao;
import dao.TestDao;
import models.*;
import models.data.AnswerData;
import models.data.QuestionData;
import models.data.TestData;
import utils.ResponseData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        TestData data = gson.fromJson(jsonReader, TestData.class);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("auth");

        TestDao testDao = new TestDao(session);
        QuestionDao questionDao = new QuestionDao(session);
        AnswerDao answerDao = new AnswerDao(session);

        if (data.getId() > 0) {//редактированние теста
            Test itemTest = testDao.item(data.getId());
            itemTest.setName(data.getName());
            itemTest.setBall(data.getBall());
            itemTest.setDescription(data.getDescription());
            testDao.update(itemTest);

            for (QuestionData questionData : data.getQuestions()) {
                int idQuestion = 0;

                if (questionData.getId() > 0){
                    Question item = questionDao.item(questionData.getId());
                    item.setTxt(questionData.getQuestionText());
                    idQuestion = item.getId();

                    questionDao.update(item);
                } else {
                    Question item = new Question(questionData.getQuestionText(), data.getId());
                    idQuestion = questionDao.add(item);
                }

                answerDao.deleteByQuestion(idQuestion);

                for (AnswerData answerData : questionData.getAnswers()) {
                    answerDao.add(new Answer(
                            answerData.getAnswerText(),
                            answerData.isAnswerCheck() ? 1 : 0,
                            idQuestion
                    ));
                }
            }

        } else { // Добавление теста
            int idTest = testDao.add(new Test(
                    data.getName(),
                    data.getDescription(),
                    data.getBall(),
                    user.getId()
            ));

            if (idTest > 0) {
                if (data.getQuestions().size() == 0) {
                    testDao.delete(idTest);
                } else {
                    for (QuestionData questionData : data.getQuestions()) {
                        int idQuestion = questionDao.add(new Question(
                                questionData.getQuestionText(),
                                idTest
                        ));

                        if (idQuestion > 0 &&  questionData.getAnswers().size() > 0) {
                            for (AnswerData answerData : questionData.getAnswers()) {
                                answerDao.add(new Answer(
                                        answerData.getAnswerText(),
                                        answerData.isAnswerCheck() ? 1 : 0,
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
        }

        responseData.setLocation("/admin/tests-view.jsp"); //куда редиректить если все ок

        resp.getWriter().print(responseData);
    }
}