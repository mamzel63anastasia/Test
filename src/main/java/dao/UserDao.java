package dao;

import models.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao {
    private Connection connection;

    public UserDao() throws SQLException, IOException, ClassNotFoundException {
        Connector connector = new Connector();
        this.connection = connector.getConnection();
    }

    public List<User> all() {
        List<User> userList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("fio"),
                        resultSet.getString("mail"),
                        resultSet.getString("tab_number"),
                        resultSet.getString("department"),
                        resultSet.getInt("id_role")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean add(User user) {

        String sql = "INSERT INTO users (login, password, fio, mail, tab_number, department, id_role) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFio());
            statement.setString(4, user.getMail());
            statement.setString(5, user.getTabNumber());
            statement.setString(6, user.getDepartment());
            statement.setInt(7, user.getRole());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User item(String login) {
        try {
            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            return fainedUser(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User item(String login, String password) {
        try {
            String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);

            return fainedUser(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User item(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            return fainedUser(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User fainedUser(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("fio"),
                    resultSet.getString("mail"),
                    resultSet.getString("tab_number"),
                    resultSet.getString("department"),
                    resultSet.getInt("id_role")


            );
        }

        return null;
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
