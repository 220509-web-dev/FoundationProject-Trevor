package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.UserDAO;
import dao.UserDBDAO;
import services.AuthService;
import services.UserService;
import servlets.AuthServlet;
import servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - Servlet context was initialized at " + LocalDateTime.now());

        UserDBDAO dbDao = new UserDBDAO();
        ObjectMapper mapper = new ObjectMapper();
        UserService userService = new UserService(dbDao);
        AuthService authService = new AuthService(userService);

        UserServlet userServlet = new UserServlet(mapper, dbDao, userService);
        AuthServlet authServlet = new AuthServlet(mapper, authService);

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - Servlet context was destroyed at " + LocalDateTime.now());

    }
}
