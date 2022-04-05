package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.dtos.NFT;
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
import java.net.URISyntaxException;
import java.util.List;

@WebServlet(name = "uploadnft", value = "/uploadnft")
@MultipartConfig(fileSizeThreshold = 720 * 720, maxFileSize = 720 * 720 * 5, maxRequestSize = 720 * 720 * 5 * 5)
public class UploadImageServlet extends HttpServlet {
    private String UPLOAD_DIRECTORY = "nftfiles";
    private NftService nftService = new NftService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hola :d");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String fCoins = req.getParameter("fcoins");
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        String dataServlet[] = {title,author,fCoins,uploadPath};
        try {
            nftService.writeNftCsv(dataServlet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<NFT> nftList = new NftService().readNftCsv().get();

        //Si la ruta no existe, lo crea
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        try{
            for(Part part : req.getParts()){
                String fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        resp.sendRedirect("./indexLOG.html");
    }
}
