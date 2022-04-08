package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.dtos.User;
import static co.edu.unbosque.p2taller3.services.UService.*;

import co.edu.unbosque.p2taller3.services.UService;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String coins = req.getParameter("coins");

        try {
            List<User> users = getUsers().get();
            User userFound = null;

            int index = 0;
            for (var user : users) {
                if (user.getUsername().equals(username)) {
                    userFound = user;
                    index = users.indexOf(user);
                    break;
                }
            }

            System.out.println(getServletContext().getRealPath("") + File.separator);
            if (userFound != null) {
                var currentCoins = Integer.parseInt(userFound.getCoins());
                var newCoins = String.valueOf((Integer.parseInt(coins) + currentCoins));
                new UService().createUser(userFound.getUsername(), userFound.getPassword(), userFound.getRole(), newCoins);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
