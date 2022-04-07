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

    private UService service = new UService();
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("hola :d");
        String Nombre = req.getParameter("name");
        String Password = req.getParameter("password");
        String Role = req.getParameter("role");
        //String dataServlet[] = {Nombre,Password,Role};
        //try {
       //     service.createUser(dataServlet);
      //  } catch (URISyntaxException e) {
      //      e.printStackTrace();
      //  }

        try {
            System.out.println(getServletContext().getRealPath("") + File.separator);
            new UService().createUser(Nombre, Password, Role, getServletContext().getRealPath("") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(Role.equals("a")){
            resp.sendRedirect("./indexLOG.html");
        }
        else if (Role.equals("b")){
            resp.sendRedirect("./indexComprador.html");
        }
    }
}