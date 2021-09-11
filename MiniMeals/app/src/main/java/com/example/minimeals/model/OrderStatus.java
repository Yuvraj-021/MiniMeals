package com.example.minimeals.model;

public class OrderStatus {
    public String messname;
    private String fname;
    private Integer Quantity;
    private Integer total;
   private String status;
   private String email;
    public OrderStatus() {
    }
    public OrderStatus(String messname,String fname, Integer quantity, Integer price,String status,String email){
        this.messname=messname;
        this.fname=fname;
        total=price;
        Quantity=quantity;
        this.status=status;
        this.email=email;
    }

    public String getMessname() {
        return messname;
    }

    public void setMessname(String messname) {
        this.messname = messname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
