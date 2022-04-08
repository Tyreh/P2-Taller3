package co.edu.unbosque.p2taller3.services;

import co.edu.unbosque.p2taller3.dtos.User;
import com.opencsv.bean.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class UService {

    static String ruta = "";

    public static Optional<List<User>> getUsers() throws IOException {
        List<User> users;
        System.out.printf(ruta);
        try (InputStream is = new FileInputStream(ruta)) {

            HeaderColumnNameMappingStrategy<User> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(User.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(br)
                        .withType(User.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                users = csvToBean.parse();
            }
        }
        return Optional.of(users);
    }

    public void createUser(String username, String password, String role, String path) throws IOException {
        String newLine = username + "," + password + "," + role + "," + "0" + "\n";

        System.out.println(path + File.separator + "Data" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "Data" + File.separator + "users.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }

    public static void main(String args[]) {

        try {
            Optional<List<User>> users = getUsers();

            for (User user : users.get()) {
                System.out.println(user.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
