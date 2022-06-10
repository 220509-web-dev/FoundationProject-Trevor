package services;

import entities.User;
import utils.exceptions.InvalidLoginException;
import utils.exceptions.UnavailableUsernameException;

public class AuthService {
    private  final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void register(User userToBeRegistered) {
        if (userService.getByUsername(userToBeRegistered.getUsername()) != null) {
            throw new UnavailableUsernameException("Username is already taken");
        }
        userService.createNewUser(userToBeRegistered);
    }

    public User login(String username, String password) {
        User user;

        user = userService.getByUsername(username);
        
        if (user != null && password.equals(user.getPassword())) {
            System.out.println("Logged in Successfully!");
            return user;
        } else if (user != null && !password.equals(user.getPassword())) {
            System.out.println("Wrong Password, try again.");
            throw new InvalidLoginException("Wrong Password Entered.");
        } else {
            System.out.println("User Does Not Exist!");
            throw new InvalidLoginException("User Does Not Exist!");
        }
    }
}
