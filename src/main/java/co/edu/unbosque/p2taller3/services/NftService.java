package co.edu.unbosque.p2taller3.services;

import co.edu.unbosque.p2taller3.dtos.NFT;
import com.opencsv.bean.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NftService {

    public List<NFT> getUsers() throws IOException {

        List<NFT> users;

        try (InputStream is = NftService.class.getClassLoader()
                .getResourceAsStream("nft.csv")) {

            HeaderColumnNameMappingStrategy<NFT> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(NFT.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                CsvToBean<NFT> csvToBean = new CsvToBeanBuilder<NFT>(br)
                        .withType(NFT.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                users = csvToBean.parse();
            }
        }

        return users;
    }

    public void createUser(String title, String author, String fcoins, String path) throws IOException {
        String newLine = "\n" + title + "," + author + "," + fcoins + "," + path;
        FileOutputStream os = new FileOutputStream("nft.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }
}
