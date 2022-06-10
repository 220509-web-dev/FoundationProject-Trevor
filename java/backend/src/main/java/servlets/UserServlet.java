package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import dao.UserDBDAO;
import dto.ResourceCreationResponse;
import entities.User;
import services.UserService;
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


    private  final ObjectMapper mapper;
    private final UserDBDAO dbDao;

    private  final UserService service;
    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
        System.out.println("[LOG] - Init param, user-servlet-key: " + this.getServletConfig().getInitParameter("user-servlet-key"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }


    public  UserServlet(ObjectMapper mapper, UserDBDAO dbDao, UserService service) {
        this.mapper = mapper;
        this.dbDao = dbDao;
        this.service = service;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - UserServlet received a GET request at " + LocalDateTime.now());

        String potentialUsername = req.getParameter("username");
        String potentialId = req.getParameter("id");


        if(potentialId != null){
            // search by Id
            User userWithId = dbDao.getById(Integer.parseInt(potentialId));

            String resPayload = mapper.writeValueAsString(userWithId);
            resp.setContentType("application/json");
            resp.getWriter().write(resPayload);

        } else if (potentialUsername != null) {
            //search by username
            User userWithName = dbDao.getByUsername(potentialUsername);

            String resPayload = mapper.writeValueAsString(userWithName);
            resp.setContentType("application/json");
            resp.getWriter().write(resPayload);
        }else {
            //search all users
            List<User> allUsers = dbDao.getAllUsers();

            String resPayload = mapper.writeValueAsString(allUsers);
            resp.setContentType("application/json");
            resp.getWriter().write(resPayload);
        }
        try {


            } catch (Exception e) {
                CustomLogger.writeToLog(e.toString());
                e.printStackTrace();
            }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[LOG] - UserServlet received a POST request at " + LocalDateTime.now());

        try {
            User newUser = mapper.readValue(req.getInputStream(), User.class);

            ResourceCreationResponse payload = service.createNewUser(newUser);
            System.out.println(newUser);
            resp.setStatus(201);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(payload));


        } catch (Exception e) {
            CustomLogger.writeToLog(e.toString());
            e.printStackTrace();
        }
    }
}
