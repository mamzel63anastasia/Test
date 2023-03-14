package dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Connector {
    private static final String base = "med-test";
    private static final String url = "jdbc:postgresql://localhost:5432/" + base;
    private static final String user = "postgres";
    private static final String password = "1234";

    private static boolean isBuildBase = false;

    private Connection connection;

    public Connector() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection(url, user, password);

        if(!isBuildBase) {
            buildBase(connection);
            isBuildBase = true;
        }

        this.connection = connection;
    }

    private void buildBase(Connection connection) throws IOException, SQLException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        File folder = new File(Objects.requireNonNull(classLoader.getResource("dao")).getPath());
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".sql");
            }
        });

        if (files != null){
            Statement statement = connection.createStatement();

            for (File file : files) {
                String sql = readFromInputStream(new FileInputStream(file));
                statement.execute(sql);
            }
        }
    }

    private String readFromInputStream(FileInputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line);
            }
        }
        return resultStringBuilder.toString();
    }

    public Connection getConnection() {
        return connection;
    }
}
