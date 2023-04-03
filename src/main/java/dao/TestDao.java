package dao;


import models.data.*;
import models.Test;
import models.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDao implements Dao {
    private Connection connection;
    private final User user;

    public TestDao(HttpSession session) {
        Connector connector = null;
        try {
            connector = new Connector();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        assert connector != null;
        this.connection = connector.getConnection();
        this.user = (User) session.getAttribute("auth");
    }

    public List<Test> all() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test WHERE id_user = ?");
            statement.setInt(1, user.getId());
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Test item(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test WHERE id = ? AND id_user = ?");
            statement.setInt(1, id);
            statement.setInt(2, user.getId());
            List<Test> list = buildStatement(statement);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Test itemByUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test WHERE id = ?");
            statement.setInt(1, id);
            List<Test> list = buildStatement(statement);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(Test test) {
        try {
            String sql = "INSERT INTO test (name, description, ball, id_user) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, test.getName());
            statement.setString(2, test.getDescription());
            statement.setInt(3, test.getBall());
            statement.setInt(4, user.getId());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public boolean update(Test test) {
        try {
            String sql = "UPDATE  test SET name = ?, description = ?, ball = ? WHERE id = ? AND id_user = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, test.getName());
            statement.setString(2, test.getDescription());
            statement.setInt(3, test.getBall());
            statement.setInt(4, test.getId());
            statement.setInt(5, user.getId());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TestData userTestBuilder(int id) {
        try {
            String sqlTest = "SELECT t.*\n" +
                    "FROM test t\n" +
                    "INNER JOIN get_test gt ON t.id = gt.id_test\n" +
                    "WHERE gt.id_users = ? AND t.id = ?";

            PreparedStatement statementTest = connection.prepareStatement(sqlTest);
            statementTest.setInt(1, user.getId());
            statementTest.setInt(2, id);

            ResultSet resultSetTest = statementTest.executeQuery();

            if (resultSetTest.next()) {
                TestData data = new TestData();
                data.setId(resultSetTest.getInt("id"));
                data.setBall(resultSetTest.getInt("ball"));
                data.setDescription(resultSetTest.getString("description"));
                data.setName(resultSetTest.getString("name"));

                PreparedStatement statementQuestion =
                        connection.prepareStatement("SELECT * FROM question WHERE id_test = ?");

                statementQuestion.setInt(1, data.getId());

                ResultSet resultSetQuestion = statementQuestion.executeQuery();
                List<QuestionData> questionDataItems = new ArrayList<>();

                while (resultSetQuestion.next()) {
                    QuestionData questionData = new QuestionData();
                    questionData.setId(resultSetQuestion.getInt("id"));
                    questionData.setQuestionText(resultSetQuestion.getString("txt"));

                    PreparedStatement statementAnswer =
                            connection.prepareStatement("SELECT * FROM answer WHERE id_question = ?");
                    statementAnswer.setInt(1, questionData.getId());

                    ResultSet resultSetAnswer = statementAnswer.executeQuery();

                    List<AnswerData> answerDataList = new ArrayList<>();

                    while (resultSetAnswer.next()) {
                        AnswerData answerData = new AnswerData();
                        answerData.setId(resultSetAnswer.getInt("id"));
                        answerData.setAnswerText(resultSetAnswer.getString("txt"));
                        answerData.setAnswerCheck(
                                resultSetAnswer.getInt("correct") == 1
                        );
                        answerDataList.add(answerData);
                    }
                    questionData.setAnswerData(answerDataList);

                    questionDataItems.add(questionData);
                }

                data.setQuestionData(questionDataItems);

                return data;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM test WHERE id = ? AND id_user = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, user.getId());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    private List<Test> buildStatement(PreparedStatement statement) {
        List<Test> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Test(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("ball"),
                        user.getId()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            connection = null;
        }
    }
}



