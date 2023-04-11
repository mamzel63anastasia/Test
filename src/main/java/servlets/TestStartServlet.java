package servlets;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import dao.AnswerDao;
import dao.QuestionDao;
import dao.StatisticDao;
import dao.TestDao;
import models.Question;
import models.Statistic;
import models.User;
import models.data.AnswerData;
import models.data.QuestionData;
import models.data.TestData;
import utils.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

        TestDao testDao = new TestDao(session);
        QuestionDao questionDao = new QuestionDao(session);
        AnswerDao answerDao = new AnswerDao(session);
        List<Integer> templateIds = answerDao.correctAnswerByTest(data.getId());
        List<Integer> userIds = new ArrayList<>();

        for (QuestionData questionData : data.getQuestions()) {
            for (AnswerData answerData : questionData.getAnswers()) {
                userIds.add(answerData.getId());
            }
        }
        double count = 0;
        int ball = 0;
        for (Integer item : userIds) {
            if (templateIds.contains(item)) {
                count++;
                if (count < templateIds.size()) {
                     ball = (int) Math.round(count / templateIds.size() * 100);
                } else {
                     ball = (int) Math.round(count / userIds.size() * 100);
                }
            }
        }

        StatisticDao statisticDao = new StatisticDao(session);
        Statistic statistic = new Statistic(
                data.getId(),
                user.getId(),
                ball
        );
        statisticDao.add(statistic);

        responseData.setLocation("/statistic.jsp");

        resp.getWriter().print(responseData);


    }
}
