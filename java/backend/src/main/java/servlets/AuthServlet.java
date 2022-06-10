package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import services.AuthService;
import utils.CustomLogger;
import utils.exceptions.InvalidLoginException;
import utils.exceptions.UnavailableUsernameException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private  final AuthService authService;

    public AuthServlet(ObjectMapper mapper, AuthService authService) {
        this.mapper = mapper;
        this.authService = authService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();

        String line;
        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String body = builder.toString();

        User userCredentials = mapper.readValue(body, User.class);

        String uri = req.getRequestURI().replace("/home/auth/", "");

        if (uri.equals("register")) {
            try {
               authService.register(userCredentials);

               PrintWriter writer = resp.getWriter();
               writer.println("User registered successfully");
               resp.setStatus(201);

            } catch (UnavailableUsernameException e) {
                CustomLogger.writeToLog(e.toString());
                e.printStackTrace();

                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.println("Username is already taken");
            }
        } else if (uri.equals("login")) {
            try {

                User user = authService.login(userCredentials.getUsername(), userCredentials.getPassword());

                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);

                PrintWriter writer = resp.getWriter();
                writer.println(mapper.writeValueAsString(user));

                resp.setStatus(202);
                resp.setContentType("application/json");
            } catch (InvalidLoginException e) {
                CustomLogger.writeToLog(e.toString());
                e.printStackTrace();

                resp.setStatus(400);
                PrintWriter writer = resp.getWriter();
                writer.println("Invalid credentials provided");
            }
        } else {
            resp.setStatus(400);
        }
    }
}
