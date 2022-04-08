package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.services.UService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;


@WebServlet(name = "Usuarios", value = "/Users")
public class SingInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            System.out.println(getServletContext().getRealPath("") + File.separator);
            new UService().createUser(nombre, password, role, getServletContext().getRealPath("") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (role.equals("artista")) {
            resp.sendRedirect("./indexLOG.html");
        } else if (role.equals("comprador")) {
            resp.sendRedirect("./indexComprador.html");
        }
    }
}