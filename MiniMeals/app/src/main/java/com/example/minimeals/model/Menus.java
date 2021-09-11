package com.example.minimeals.model;

public class Menus {
    private String FoodName;
    private Integer Price;
    private String imageURL;
    public String messName;
    private String userid;
private String description;


    public Menus() {
    }

    public Menus(String foodName, Integer price, String imageURL, String userid,String messName,String description) {
        FoodName = foodName;
        Price = price;
        this.imageURL = imageURL;
        this.userid = userid;
        this.messName=messName;
        this.description=description;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserid(String s) {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
