package dao;

import models.Material;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDao {
    private Connection connection;

    public MaterialDao() throws SQLException, IOException, ClassNotFoundException {
        Connector connector = new Connector();
        this.connection = connector.getConnection();
    }

    public List<Material> getAllMaterials() {
        List<Material> materialList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM material";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Material material = new Material(
                        resultSet.getString("name_material"),
                        resultSet.getString("text_material"),
                        resultSet.getInt("id_user")
                );
                materialList.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialList;
    }

    public boolean addMaterial(Material material) {

        String sql = "INSERT INTO material (name_material, text_material) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, material.getNameMaterial());
            statement.setString(2, material.getTextMaterial());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMaterialById(int id) {
        String sql = "DELETE material WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
