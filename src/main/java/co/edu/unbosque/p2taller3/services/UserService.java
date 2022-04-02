package co.edu.unbosque.p2taller3.services;

import co.edu.unbosque.p2taller3.dtos.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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

public class UserService {
    public Optional<List<User>> readUserCsv() {
        try {
            List<User> userList = new ArrayList<>();

            Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("users.csv").toURI()));
            CSVReader csvReader = new CSVReader(reader);

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                String username = line[0];
                String password = line[1];
                String role = line[2];
                User user = new User(username, password, role);
                userList.add(user);
            }
            reader.close();
            csvReader.close();
            return Optional.of(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void writeUserCsv(String[] data) throws URISyntaxException {
        File file = new File(String.valueOf(Paths.get(ClassLoader.getSystemResource("users.csv").toURI())));
        try {
            FileWriter outPutFile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outPutFile);
            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
