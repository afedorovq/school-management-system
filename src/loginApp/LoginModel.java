package loginApp;

import dbUtil.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    private Connection connection;

    public LoginModel() {
        try {
            connection = DatabaseConnector.getDatabaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return connection != null;
    }

    public boolean isLogin(String user, String pass, String option) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * " +
                "FROM login " +
                "WHERE username = ?" +
                "AND password = ?" +
                "AND division = ?;";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, pass);
        preparedStatement.setString(3, option);

        resultSet = preparedStatement.executeQuery();


        if (resultSet.next()) {
            return true;
        }

        preparedStatement.close();
        resultSet.close();

        return false;
    }
}