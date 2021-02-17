package se.lexicon;

import java.sql.*;

public class JdbcExample {

    public static void main(String[] args) {
        //findAllCities();
        //findAllCitiesByName("Amsterdam");
        //findCityById(6);
        //findCityByIdAndName(6, "Rotterdam");

        //findCityById(6);
        //deleteCityById(6);

        findCityById(11);
        updateCityNameById(11, "TEST");
        findCityById(11);

    }

    public static Connection mySqlConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void findAllCities() {
        try {
            Statement statement = mySqlConnection().createStatement();
            String selectAllCities = "select id,name,population from city";
            ResultSet resultSet = statement.executeQuery(selectAllCities);
            while (resultSet.next()) {
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

    public static void findAllCitiesByName(String name) {
        // step 1: use connection
        Connection connection = mySqlConnection();
        // step 2: define query
        String query = "select * from city where name like '" + name + "'";
        try (
                // step 3: define query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findCityById(int id) {
        Connection connection = mySqlConnection();
        String query = "select id,name from city where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name"));
            } else {
                System.out.println("data not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void findCityByIdAndName(int id, String name) {
        Connection connection = mySqlConnection();
        String query = "select id,name from city where id = ? and name = ?";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " - " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void deleteCityById(int id) {
        Connection connection = mySqlConnection();
        String query = "delete from city where id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            System.out.println("delete result: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCityNameById(int id, String name) {
        Connection connection = mySqlConnection();
        String query = "update city set name = ? where id = ? ";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);
            int result = preparedStatement.executeUpdate();
            /*
            if (result == 1){
                System.out.println("##### operation id done");
            }else {
                System.out.println("@@@@@ operation is not done");
            }
             */
            // using ternary operation
            System.out.println((result == 1) ? "Ok" : "Not Ok");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
