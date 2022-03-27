package co.edu.unbosque.p2taller3;

import java.io.*;
import java.util.List;
import java.util.Optional;

import co.edu.unbosque.p2taller3.dtos.User;
import co.edu.unbosque.p2taller3.services.UserService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        List<User> userList = new UserService().readUsersFromCsv().get();
        User userFound = userList.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword())).findFirst().get();


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}