package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContext {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "VijiViji1515";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Other methods for handling database interactions can be added here
}
