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
//        String coins = req.getParameter("coins");
//
//        try {
//            var users = getUsers().get();
//            var user = new User(logInServlet.getUsername(), logInServlet.getPassword(), logInServlet.getRole());
//            users.remove(user);
//
//            System.out.println(getServletContext().getRealPath("") + File.separator);
//            for (var userInList : users) {
//                new UService().createUser(userInList, getServletContext().getRealPath("") + File.separator, true);
//            }
//
//            user.setCoins(String.valueOf(Integer.parseInt(user.getCoins()) + Integer.parseInt(coins)));
//            new UService().createUser(user, getServletContext().getRealPath("") + File.separator, true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    }
}
