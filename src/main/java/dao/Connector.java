package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String base = "med-test";
    private static final String url = "jdbc:postgresql://localhost:5432/" + base;
    private static final String user = "postgres";
    private static final String password = "1234";

    private final Connection connection;

    public Connector() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        this.connection = DriverManager.getConnection(url, user, password);
    }

    public Connection getConnection() {
        return connection;
    }
}
