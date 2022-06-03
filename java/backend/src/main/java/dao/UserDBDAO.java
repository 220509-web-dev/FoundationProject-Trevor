package dao;

import entities.User;
import utils.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBDAO implements UserDAO {

    @Override
    public User getById(int id) {
       try (Connection con = ConnectionUtility.getConnection()) {

           String sql = "SELECT * FROM app_users WHERE id = ?";
           PreparedStatement preparedStatement = con.prepareStatement(sql);
           preparedStatement.setInt(1, id);

           ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
               return new User(
                       resultSet.getInt("id"),
                       resultSet.getString("first_name"),
                       resultSet.getString("last_name"),
                       resultSet.getString("email"),
                       resultSet.getString("username"),
                       resultSet.getString("password")
               );
           }

       } catch (SQLException e) {
           System.out.println("Something went wrong with the database");
           e.printStackTrace();
       }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        try(Connection connection = ConnectionUtility.getConnection()) {
            String sql = " SELECT * FROM app_users WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        }catch (SQLException e) {
            System.out.println(" something went wrong getting by username");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection con = ConnectionUtility.getConnection()){
            String sql = "SELECT * FROM app_users";

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
               userList.add(new User(
                       resultSet.getInt("id"),
                       resultSet.getString("first_name"),
                       resultSet.getString("last_name"),
                       resultSet.getString("email"),
                       resultSet.getString("username"),
                       resultSet.getString("password")
               ));

            }
            return userList;
        } catch (SQLException e) {
            System.out.println(" Something went wrong retrieving the users");
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public int create(User newUser) {
        try (Connection con = ConnectionUtility.getConnection()){
            String sql = "INSERT INTO app_users (first_name, last_name, email, username, password) "
                        + "VALUES (?, ?, ?, ?, ?) "
                        + "RETURNING app_users.id";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setString(3, newUser.getEmail());
            preparedStatement.setString(4, newUser.getUsername());
            preparedStatement.setString(5, newUser.getPassword());

            ResultSet resultSet;

            if ((resultSet = preparedStatement.executeQuery()) != null) {
                System.out.println(" Welcome new user");
                resultSet.next();
                return resultSet.getInt(1);


            }
        } catch (SQLException e) {
            System.out.println(" User creation failed");
            e.printStackTrace();

        }
        return newUser.getId();
    }
}
