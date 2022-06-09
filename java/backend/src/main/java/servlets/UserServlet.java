package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import dao.UserDBDAO;
import entities.User;
import utils.CustomLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;


public class UserServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
        System.out.println("[LOG] - Init param, user-servlet-key: " + this.getServletConfig().getInitParameter("user-servlet-key"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }

    private  final ObjectMapper mapper;

    public  UserServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - UserServlet received a request at " + LocalDateTime.now());
        // Find all users
            try {
                UserDAO dao = new UserDBDAO();
                List<User> allUsers = dao.getAllUsers();
                String allUsersString = allUsers.toString();


                String resPayload = mapper.writeValueAsString(allUsers);
                resp.setContentType("application/json");
                resp.getWriter().write(resPayload);

                System.out.println(allUsersString);
            } catch (Exception e) {
                CustomLogger.writeToLog(e.toString());
                e.printStackTrace();
            }
            // Find by id
        String username = req.getParameter("username");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - UserServlet received a request at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
       User newUser =  mapper.readValue(req.getInputStream(), User.class);
       System.out.println(newUser);

        resp.setStatus(204);
    }
}
