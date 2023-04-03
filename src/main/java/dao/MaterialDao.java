package dao;

import models.Material;
import models.User;


import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialDao implements Dao{
    private Connection connection;
    private final User user;

    public MaterialDao(HttpSession session) {
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

    public List<Material> all() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM material WHERE id_user = ?");
            statement.setInt(1, user.getId());
            return buildStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Material item(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM material WHERE id = ? AND id_user = ?");
            statement.setInt(1, id);
            statement.setInt(2, user.getId());
            List<Material> list = buildStatement(statement);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Material itemByUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM material WHERE id = ?");
            statement.setInt(1, id);
            List<Material> list = buildStatement(statement);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Material material) {
        try {
            String sql = "INSERT INTO material (name, txt, id_user) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, material.getName());
            statement.setString(2, material.getText());
            statement.setInt(3, user.getId());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Material material) {
        try {
            String sql = "UPDATE material SET name = ?, txt = ? WHERE id = ? AND id_user = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, material.getName());
            statement.setString(2, material.getText());
            statement.setInt(3, material.getId());
            statement.setInt(4, user.getId());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM material WHERE id = ? AND id_user = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, user.getId());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private List<Material> buildStatement(PreparedStatement statement) {
        List<Material> list = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Material(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("txt"),
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
