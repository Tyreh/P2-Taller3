package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.dtos.User;

import static co.edu.unbosque.p2taller3.services.UService.*;

import co.edu.unbosque.p2taller3.services.UService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static co.edu.unbosque.p2taller3.services.UService.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Wallet", value = "/Wallet")
public class WalletServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String coins = req.getParameter("coins");

        try {
            List<User> users = getUsers().get();
            User userFound = null;

            for (var user : users) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    userFound = user;
                    users.remove(userFound);
                    break;
                }
            }

            if (userFound != null) {
                new UService().createUser("username", "password", "role", "coins", getServletContext().getRealPath("") + File.separator, false);

                for (var user : users) {
                    new UService().createUser(user.getUsername(), user.getPassword(), user.getRole(), user.getCoins(), getServletContext().getRealPath("") + File.separator, true);
                }

                var currentCoins = userFound.getCoins();
                var newCoins = String.valueOf(Integer.parseInt(currentCoins) + Integer.parseInt(coins));
                userFound.setCoins(newCoins);
                new UService().createUser(userFound.getUsername(), userFound.getPassword(), userFound.getRole(), userFound.getCoins(), getServletContext().getRealPath("") + File.separator, true);

                req.setAttribute("role", userFound.getRole());
                req.setAttribute("coins", userFound.getCoins());
                req.setAttribute("username", userFound.getUsername());

                if (userFound.getRole().equals("artista")) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("./indexLOG.jsp");
                    dispatcher.forward(req, resp);
                } else if (userFound.getRole().equals("comprador")) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("./indexComprador.jsp");
                    dispatcher.forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("./indexLOG.html");
    }
}
