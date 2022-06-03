package dao;

import entities.User;

import java.util.List;

public interface UserDAO {
    User getById(int id);
    User getByUsername(String username);
    List<User> getAllUsers();
    int create(User newUser);

}
