package dao;

import models.Answer;


import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerDao implements Dao {

    private Connection connection;


    public AnswerDao(HttpSession session) {
        Connector connector = null;
        try {
            connector = new Connector();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        assert connector != null;
        this.connection = connector.getConnection();

    }

    public List<Answer> all() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM answer ");
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Answer> all(int questionId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM answer WHERE id_question = ?");
            statement.setInt(1, questionId);
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean add(Answer answer) {
        try {
            String sql = "INSERT INTO answer (txt, correct, id_question) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, answer.getTxt());
            statement.setInt(2, answer.getCorrect());
            statement.setInt(3, answer.getId_question());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(Answer answer) {
        try {
            String sql = "UPDATE  answer SET txt = ? WHERE id = ? AND id_question = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, answer.getTxt());
            statement.setInt(2, answer.getId());
            statement.setInt(3, answer.getId_question());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM answer WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteByQuestion(int idQuestion) {
        try {
            String sql = "DELETE FROM answer WHERE id_question = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, idQuestion);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Answer> buildStatement(PreparedStatement statement) {
        List<Answer> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Answer(
                        resultSet.getInt("id"),
                        resultSet.getString("txt"),
                        resultSet.getInt("correct"),
                        resultSet.getInt("id_question")
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
