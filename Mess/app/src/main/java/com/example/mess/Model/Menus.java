package com.example.mess.Model;

public class Menus {
    private String FoodName;
    private Integer Price;
    private String imageURL;
    public String imageName;
    private String userid;
    private Integer val;
    private String messName;
    private String Description;

    public Menus() {
    }


    public Menus(String foodName, Integer price, String description, String imageURL, String userid, Integer val, String messName) {
        FoodName = foodName;
        Price = price;
        Description = description;
        this.imageURL = imageURL;
        this.userid = userid;
        this.val = val;
        this.messName = messName;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }
}
