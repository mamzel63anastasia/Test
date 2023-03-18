package dao;

import models.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao implements Dao{
    private Connection connection;

    public RoleDao() throws SQLException, ClassNotFoundException {
        Connector connector = new Connector();
        this.connection = connector.getConnection();
    }

    public List<Role> allRoles() {
        List<Role> roles = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM role");

            while (resultSet.next()){
                roles.add(new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
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
