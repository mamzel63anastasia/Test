package dao;

import models.GetTest;
import models.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetTestDao implements Dao {
    private Connection connection;
    private final User user;

    public GetTestDao(HttpSession session) {
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

    public List<GetTest> all() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_test WHERE id_admin = ?");
            statement.setInt(1, user.getId());
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<GetTest> allByUser() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_test WHERE id_users = ?");
            statement.setInt(1, user.getId());
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public GetTest item(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM get_test WHERE id = ? AND id_admin = ?");
            statement.setInt(1, id);
            statement.setInt(2, user.getId());
            List<GetTest> list = buildStatement(statement);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean add(GetTest getTest) {
        try {
            String sql = "INSERT INTO get_test (id_users, id_test, id_material, id_admin) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, getTest.getId_user());
            statement.setInt(2, getTest.getId_test());
            statement.setInt(3, getTest.getId_material());
            statement.setInt(4, user.getId());
            return statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private List<GetTest> buildStatement(PreparedStatement statement) {
        List<GetTest> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new GetTest(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_users"),
                        resultSet.getInt("id_test"),
                        resultSet.getInt("id_material"),
                        resultSet.getInt("id_admin")
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
