package co.edu.unbosque.p2taller3.services;

import co.edu.unbosque.p2taller3.dtos.User;
import com.opencsv.bean.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * The type U service.
 */
public class UService {

    /**
     * The Ruta.
     */
    static String ruta = "";

    /**
     * Gets users.
     *
     * @return the users
     * @throws IOException the io exception
     */
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

    /**
     * Create user.
     *
     * @param username the username
     * @param password the password
     * @param role     the role
     * @param path     the path
     * @throws IOException the io exception
     */
    public void createUser(String username, String password, String role, String path) throws IOException {
        String newLine = username + "," + password + "," + role + "," + "0" + "\n";

        System.out.println(path + File.separator + "Data" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "Data" + File.separator + "users.csv", true);
        os.write(newLine.getBytes());
        os.close();
    }

    /**
     * Create user.
     *
     * @param username the username
     * @param password the password
     * @param role     the role
     * @param coins    the coins
     * @param path     the path
     * @param append   the append
     * @throws IOException the io exception
     */
    public void createUser(String username, String password, String role, String coins, String path, boolean append) throws IOException {
        String newLine = username + "," + password + "," + role + "," + coins + "\n";

        System.out.println(path + File.separator + "Data" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "Data" + File.separator + "users.csv", append);
        os.write(newLine.getBytes());
        os.close();
    }

    /**
     * Create user.
     *
     * @param username the username
     * @param password the password
     * @param role     the role
     * @param path     the path
     * @param append   the append
     * @throws IOException the io exception
     */
    public void createUser(String username, String password, String role, String path, boolean append) throws IOException {
        String newLine = username + "," + password + "," + role + "," + "0" + "\n";

        System.out.println(path + File.separator + "Data" + File.separator + "users.csv" + "Create");
        FileOutputStream os = new FileOutputStream(path + "Data" + File.separator + "users.csv", append);
        os.write(newLine.getBytes());
        os.close();
    }

    /**
     * Gets ruta.
     *
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Sets ruta.
     *
     * @param ruta the ruta
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
