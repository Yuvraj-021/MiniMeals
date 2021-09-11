package com.example.mess.Model;

public class MessRequests {
    private String foodName;
    private Integer inprice;
    private Integer quantity;
    private String messName;
    private String Description;
    private String Address;
    private String Mobileno;
    private String userid;
    private String Owername;

    public MessRequests() {
    }


    public MessRequests(String foodName, Integer inprice, Integer quantity, String messName, String description, String address, String mobileno, String userid,String owername) {
        this.foodName = foodName;
        this.inprice = inprice;
        this.quantity = quantity;
        this.messName = messName;
        Description = description;
        Address = address;
        Mobileno = mobileno;
        this.userid = userid;
        Owername = owername;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getInprice() {
        return inprice;
    }

    public void setInprice(Integer inprice) {
        this.inprice = inprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOwername() {
        return Owername;
    }

    public void setOwername(String owername) {
        Owername = owername;
    }
}
