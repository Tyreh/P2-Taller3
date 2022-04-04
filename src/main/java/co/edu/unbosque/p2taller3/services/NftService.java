package co.edu.unbosque.p2taller3.services;

import co.edu.unbosque.p2taller3.dtos.NFT;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NftService {
    public Optional<List<NFT>> readNftCsv(){
        try{
            List<NFT> nftList = new ArrayList<>();

            Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("nft.csv").toURI()));
            CSVReader csvReader = new CSVReader(reader);

            String[] line;
            while((line = csvReader.readNext()) != null){
                String title = line[0];
                String fCoins = line[1];
                String imgPath = line[2];

                NFT nft = new NFT(title,fCoins,imgPath);
                nftList.add(nft);
            }
            reader.close();
            csvReader.close();
            return Optional.of(nftList);
        } catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void writeNftCsv(String[] data) throws URISyntaxException{
        File file = new File(String.valueOf(Paths.get(ClassLoader.getSystemResource("nft.csv").toURI())));
        try{
            FileWriter outputFile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputFile);
            writer.writeNext(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
