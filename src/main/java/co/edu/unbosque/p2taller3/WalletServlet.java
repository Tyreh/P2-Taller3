package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.dtos.User;
import co.edu.unbosque.p2taller3.services.UService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static co.edu.unbosque.p2taller3.services.UService.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "Wallet", value = "/Wallet")
public class WalletServlet extends HttpServlet {

    private final LogInServlet logInServlet = new LogInServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String coins = req.getParameter("coins");

        try {
            var users = getUsers().get();
            users.removeIf(user -> user.getUsername().equals(logInServlet.getUsername()));
            System.out.println(getServletContext().getRealPath("") + File.separator);

            new UService().createUser(logInServlet.getUsername(), logInServlet.getPassword(), logInServlet.getRole(), getServletContext().getRealPath("") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
