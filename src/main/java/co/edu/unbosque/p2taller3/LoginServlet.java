package co.edu.unbosque.p2taller3;

import java.io.*;
import java.util.List;

import co.edu.unbosque.p2taller3.dtos.User;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        List<User> userList = new UserService().readUserCsv().get();
        User userFound = userList.stream().filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword())).findFirst().get();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}