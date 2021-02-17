package se.lexicon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {

    public static void main(String[] args) {

        // define my sql connection
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");
            // define statement
            Statement statement= connection.createStatement();
            String selectAllCities= "select * from city";
            // execute select query
            statement.executeQuery(selectAllCities);
            System.out.println("Connection is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
