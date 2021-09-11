package com.example.mini.Model;

public class Requests {
    public String messName;
    private String FoodName;
    private Integer Quantity;
    private Integer total;
    private String Email;
    private String Address;
    private String Mobileno;
    private String customerName;
    public Requests() {
    }
    public Requests(String messname,String foodName, Integer quantity, Integer price, String email,String address,String mobileno,String customername) {
        FoodName = foodName;
        total = price;
        Quantity=quantity;
        Email =email;
        messName=messname;
        Address=address;
        Mobileno=mobileno;
        customerName=customername;
    }

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
