package dao;


import models.Question;


import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionDao implements Dao {
    private Connection connection;


    public QuestionDao(HttpSession session) {
        Connector connector = null;
        try {
            connector = new Connector();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        assert connector != null;
        this.connection = connector.getConnection();

    }

    public List<Question> all() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM question ");
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Question> all(int idTest) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM question WHERE id_test = ?");
            statement.setInt(1, idTest);
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Question item(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM question WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("txt"),
                        resultSet.getInt("id_test")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(Question question) {
        try {
            String sql = "INSERT INTO question (txt, id_test) VALUES (?, ?) RETURNING id";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, question.getTxt());
            statement.setInt(2, question.getId_test());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean update(Question question) {
        try {
            String sql = "UPDATE  question SET txt = ? WHERE id = ? AND id_test = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, question.getTxt());
            statement.setInt(2, question.getId());
            statement.setInt(3, question.getId_test());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM question WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private List<Question> buildStatement(PreparedStatement statement) {
        List<Question> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("txt"),
                        resultSet.getInt("id_test")
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
