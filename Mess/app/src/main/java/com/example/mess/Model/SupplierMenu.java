package com.example.mess.Model;

public class SupplierMenu {
    private String FoodName;
    private Integer Price;
    private String imageURL;
    public String imageName;
    private String userid;
    private Integer val;
    private String messName;
    private String Description;
    private Integer quantity;

    public SupplierMenu() {
    }

    public SupplierMenu(String foodName, Integer price, String imageURL, String imageName, String userid, Integer val, String messName, String description, Integer quantity) {
        FoodName = foodName;
        Price = price;
        this.imageURL = imageURL;
        this.imageName = imageName;
        this.userid = userid;
        this.val = val;
        this.messName = messName;
        Description = description;
        this.quantity = quantity;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
