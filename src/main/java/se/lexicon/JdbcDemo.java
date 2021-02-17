package se.lexicon;

import java.sql.*;

public class JdbcDemo {

    public static void main(String[] args) {

        // define my sql connection
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "root");
            // define statement
            Statement statement = connection.createStatement();
            String selectAllCities = "select id,name,population from city";
            // execute select query
            ResultSet resultSet = statement.executeQuery(selectAllCities);
            // get data using resultSet
            while (resultSet.next()) {
                /*
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                 */
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String population = resultSet.getString("population");
                System.out.println("Id: " + id + " Name: " + name + " Population: " + population);
            }
            System.out.println("Connection is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
