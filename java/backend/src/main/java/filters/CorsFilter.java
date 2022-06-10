package filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class CorsFilter extends HttpFilter {

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - Cors Filter initialized at " + LocalDateTime.now());
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, " +
                "Access-Control-Request-Method, Access-Control-Request-Headers");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req,res);
    }
}
