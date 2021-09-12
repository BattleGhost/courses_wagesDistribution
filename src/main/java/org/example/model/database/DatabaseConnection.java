package org.example.model.database;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public void createConnection() throws SQLException {
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(DatabaseConstants.URL, DatabaseConstants.USERNAME,
                DatabaseConstants.PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
