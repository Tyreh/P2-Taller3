package co.edu.unbosque.p2taller3;

import java.io.*;
import java.util.List;

import co.edu.unbosque.p2taller3.dtos.User;
import co.edu.unbosque.p2taller3.services.UService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LogInServlet extends HttpServlet {

    private UService uService;

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        uService = new UService();
        uService.setRuta(getServletContext().getRealPath("")+ File.separator + "Data"+File.separator+"users.csv");


        List<User> users = new UService().getUsers().get();



        User userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()) &&  role.equals(user.getRole()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {
            request.setAttribute("role", userFounded.getRole());

            Cookie cookie = new Cookie("role", userFounded.getRole());
            cookie.setMaxAge(20);
            response.addCookie(cookie);

            //RequestDispatcher dispatcher = request.getRequestDispatcher("./home.jsp");
            if(role.equals("artista")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("./indexLOG.html");
                dispatcher.forward(request, response);
            }
            else if (role.equals("comprador")){
                RequestDispatcher dispatcher = request.getRequestDispatcher("./indexComprador.html");
                dispatcher.forward(request, response);
            }

        } else {
            response.sendRedirect("./401.html");
        }
    }

    public void destroy() {}
}