package co.edu.unbosque.p2taller3.dtos;

import java.util.Objects;

/**
 * The type Nft.
 */
public class NFT {

    private String title;
    private String author;
    private String FCoins;
    private String imagePath;

    /**
     * Instantiates a new Nft.
     *
     * @param title     the title
     * @param author    the author
     * @param FCoins    the f coins
     * @param imagePath the image path
     */
    public NFT(String title, String author, String FCoins, String imagePath) {
        this.title = title;
        this.FCoins = FCoins;
        this.imagePath = null;
        this.author = author;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets f coins.
     *
     * @return the f coins
     */
    public String getFCoins() {
        return FCoins;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets f coins.
     *
     * @param FCoins the f coins
     */
    public void setFCoins(String FCoins) {
        this.FCoins = FCoins;
    }

    @Override
    public String toString() {
        return "NFT{" +
                "title='" + title + '\'' +
                ", FCoins='" + FCoins + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

}
