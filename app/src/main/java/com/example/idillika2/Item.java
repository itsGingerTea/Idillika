package com.example.idillika2;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    private int id;

    @SerializedName("imageLink")
    private String image;

    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private String price;

    @SerializedName("available")
    private boolean available;

    @SerializedName("favorite")
    private boolean favorite;

    @SerializedName("brand")
    private String brand;

    private boolean isFavoriteInPrefs;

    public Item(int id, String image, String title, String price, String brand) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
        this.brand = brand;
        this.isFavoriteInPrefs = false; //изначально (в полученном виде из апи) это фолс
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public void setFavoriteInPrefs(boolean favoriteInPrefs) {//? так же да
        isFavoriteInPrefs = favoriteInPrefs;
    }

    public boolean isFavoriteInPrefs() {
        return isFavoriteInPrefs;
    }

    public String getBrand() {
        return brand;
    }
}
