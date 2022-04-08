package co.edu.unbosque.p2taller3;

import java.io.*;
import java.util.List;

import co.edu.unbosque.p2taller3.dtos.User;
import static co.edu.unbosque.p2taller3.services.UService.*;

import co.edu.unbosque.p2taller3.services.UService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LogInServlet extends HttpServlet {

    private String username;
    private String password;
    private String role;

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        username = request.getParameter("name");
        password = request.getParameter("password");
        role = request.getParameter("role");

        UService uService = new UService();
        uService.setRuta(getServletContext().getRealPath("") + File.separator + "Data" + File.separator + "users.csv");

        List<User> users = getUsers().get();

        User userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()) && role.equals(user.getRole()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {
            request.setAttribute("role", userFounded.getRole());
            request.setAttribute("coins", userFounded.getCoins());

            Cookie cookie = new Cookie("role", userFounded.getRole());
            cookie.setMaxAge(20);
            response.addCookie(cookie);

            if (role.equals("artista")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./indexLOG.jsp");
                dispatcher.forward(request, response);
            } else if (role.equals("comprador")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("./indexComprador.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            response.sendRedirect("./401.html");
        }
    }

    public void destroy() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}