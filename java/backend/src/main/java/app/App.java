package app;


import dao.UserDAO;
import dao.UserDBDAO;
import entities.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDBDAO();
        User user = new User(5,"John", "Wick", "jwick85@gmail.com", "immortal99", "cantkill99");
        System.out.println(user);
    }
}
