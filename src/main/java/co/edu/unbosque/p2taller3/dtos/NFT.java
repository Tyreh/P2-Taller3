package co.edu.unbosque.p2taller3.dtos;

import java.util.Objects;

public class NFT {

    private String title;
    private String author;
    private String FCoins;
    private String imagePath;

    public NFT(String title, String author, String FCoins, String imagePath) {
        this.title = title;
        this.FCoins = FCoins;
        this.imagePath = imagePath;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getFCoins() {
        return FCoins;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NFT nft = (NFT) o;
        return Objects.equals(title, nft.title) && Objects.equals(FCoins, nft.FCoins) && Objects.equals(imagePath, nft.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, FCoins, imagePath);
    }
}
