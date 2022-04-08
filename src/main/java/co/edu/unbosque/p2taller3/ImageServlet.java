package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.services.NftService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(name = "uploadNft", value = "/upload-test")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ImageServlet extends HttpServlet {

    private String UPLOAD_DIRECTORY = "uploads";

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String fcoins = request.getParameter("fcoins");
        String pathAbs = getServletContext().getRealPath("") + File.separator;

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        new NftService().createUser(title,author,fcoins,pathAbs);

        if(!uploadDir.exists()){ uploadDir.mkdir();}

        try{
            for(Part part : request.getParts()){
                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        response.sendRedirect("./indexLOG.html");
    }

    public void destroy(){}
}