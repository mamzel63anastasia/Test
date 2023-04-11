package dao;

import models.Statistic;
import models.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticDao implements Dao {
    private Connection connection;
    private final User user;

    public StatisticDao(HttpSession session) {
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

    public List<Statistic> all() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM statistic WHERE id_user = ? ORDER BY data, id DESC");
            statement.setInt(1, user.getId());
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public int checkUserCount(int idTest) {
        try {
            String sql = "SELECT count(id) cnt FROM statistic WHERE id_user = ? AND id_test = ? AND data = now()::date";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setInt(2, idTest);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return resultSet.getInt("cnt");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Statistic> allByAdmin() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT s.* FROM statistic s " +
                    "INNER JOIN test t on s.id_test = t.id " +
                    "WHERE t.id_user = ? " +
                    "ORDER BY s.data DESC ");
            statement.setInt(1, user.getId());
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean add(Statistic statistic) {
        try {
            String sql = "INSERT INTO statistic (data, id_test, id_user, ball) VALUES (now(), ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, statistic.getIdTest());
            statement.setInt(2, user.getId());
            statement.setInt(3, statistic.getBall());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<Statistic> buildStatement(PreparedStatement statement) {
        List<Statistic> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Statistic(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_test"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("ball"),
                        resultSet.getDate("data")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() {

    }
}
