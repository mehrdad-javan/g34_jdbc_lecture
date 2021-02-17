package se.lexicon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDemo {

    public static void main(String[] args) {

        // define my sql connection
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","root");

            System.out.println("Connection is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
