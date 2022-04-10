package co.edu.unbosque.p2taller3.dtos;

import java.util.Objects;

/**
 * The type User.
 */
public class User {
    private String username;
    private String password;
    private String role;
    private String coins;

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets coins.
     *
     * @return the coins
     */
    public String getCoins() {
        return coins;
    }

    /**
     * Sets coins.
     *
     * @param coins the coins
     */
    public void setCoins(String coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User{" + "username = " + username + ", password = " + password + ", role =" + role + "}";
    }

}
