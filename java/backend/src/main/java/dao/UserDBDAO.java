package dao;

import entities.User;
import utils.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBDAO implements UserDAO {

    @Override
    public User getById(int id) {
       try (Connection con = ConnectionUtility.getInstance().getConnection() ){

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
        try(Connection connection = ConnectionUtility.getInstance().getConnection()) {
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
        try (Connection con = ConnectionUtility.getInstance().getConnection()){
            String sql = "SELECT * FROM app_users";

            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
               User user = new User();

               user.setId(resultSet.getInt("id"));
               user.setFirstName(resultSet.getString("first_name"));
               user.setLastName(resultSet.getString("last_name"));
               user.setEmail(resultSet.getString("email"));
               user.setUsername(resultSet.getString("username"));
               user.setPassword(resultSet.getString("password"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            System.out.println(" Something went wrong retrieving the users");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User create(User newUser) {
        try (Connection con = ConnectionUtility.getInstance().getConnection()){
            String sql = "INSERT INTO app_users (first_name, last_name, email, username, password) "
                        + "VALUES (?, ?, ?, ?, ?) "
                        + "RETURNING app_users.id";

            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, newUser.getFirstName());
            preparedStatement.setString(2, newUser.getLastName());
            preparedStatement.setString(3, newUser.getEmail());
            preparedStatement.setString(4, newUser.getUsername());
            preparedStatement.setString(5, newUser.getPassword());

            int rowsInserted =  preparedStatement.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int newId = resultSet.getInt("id");

                newUser.setId(newId);
                return newUser;
            }
        } catch (SQLException e) {
            System.out.println(" User creation failed");
            e.printStackTrace();

        }
        return null;
    }
}
