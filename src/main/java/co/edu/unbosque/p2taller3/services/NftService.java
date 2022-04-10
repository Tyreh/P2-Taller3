package co.edu.unbosque.p2taller3.services;

import co.edu.unbosque.p2taller3.dtos.NFT;
import com.opencsv.bean.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

/**
 * The type Nft service.
 */
public class NftService {

    /**
     * Gets nfts.
     *
     * @return the nfts
     * @throws IOException the io exception
     */
    public List<NFT> getNfts() throws IOException {

        List<NFT> nfts;

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

                nfts = csvToBean.parse();
            }
        }

        return nfts;
    }

    /**
     * Generate random words string.
     *
     * @param numberOfWords the number of words
     * @return the string
     */
    public String generateRandomWords(int numberOfWords) {
        StringBuilder randomStrings = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(8) + 3];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings.append(word);
        }
        return randomStrings.toString();
    }

    /**
     * Create nft.
     *
     * @param title     the title
     * @param author    the author
     * @param coins     the coins
     * @param path      the path
     * @param imagePath the image path
     * @throws IOException the io exception
     */
    public void createNft(String title, String author, String coins, String path, String imagePath) throws IOException {
        String newLine = "\n" + title + "," + author + "," + coins + "," + imagePath;

        System.out.println(path + File.separator + "Data" + File.separator + "nft.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "Data" + File.separator + "nft.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }
}
