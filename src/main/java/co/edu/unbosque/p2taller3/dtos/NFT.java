package co.edu.unbosque.p2taller3.dtos;

public class NFT {

    private String title;
    private String FCoins;

    public NFT(String title, String FCoins) {
        this.title = title;
        this.FCoins = FCoins;
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

}
