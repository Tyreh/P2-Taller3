package co.edu.unbosque.p2taller3;

import co.edu.unbosque.p2taller3.dtos.NFT;
import co.edu.unbosque.p2taller3.services.NftService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig
public class ImageServlet extends HttpServlet {

    private NftService nftc = new NftService();
    private String pathFiles = "src/main/resources/nftfiles";
    private File uploads = new File(pathFiles);
    private String extens[] = {".jpg", ".png", ".gif", "jpeg"};

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action){
            case "add":
                saveAuthor(req, resp);
                break;
        }
    }

    private void saveAuthor(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try{

            String title = req.getParameter("title");
            String fcoins  = req.getParameter("fcoins");
            Part part = req.getPart("file");
            String partConverted = part.toString();

            if(part == null){
                System.out.println("No ha seleccionado un archivo");
                return;
            }

            if(isExtension(part.getSubmittedFileName(), extens)){
                String photo = saveFile(part, uploads);
                NFT nft = new NFT(title, fcoins, partConverted);
                //funcion agregar nft
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        res.sendRedirect("createNFT.html");
    }


    private String saveFile(Part part, File pathUploads){
        String pathAbs = "";

        try {

            Path path = Paths.get(part.getSubmittedFileName());
            String fileName = path.getFileName().toString();
            InputStream input = part.getInputStream();

            if(input != null){
                File file = new File(pathUploads, fileName);
                pathAbs = file.getAbsolutePath();
                Files.copy(input,file.toPath());
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return pathAbs;
    }

    private boolean isExtension(String fileName, String[] extensions){
        for(String et : extensions){
            if(fileName.toLowerCase().endsWith(et)){
                return true;
            }
        }
        return false;
    }
}
