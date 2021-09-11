package com.example.mess.Model;

public class Requests {
    private String foodName;
    private Integer total;
    private Integer quantity;
    private String messName;
    private String CustomerName;
    private String Email;
    public Requests() {
    }

    public Requests(String foodName, Integer total, Integer quantity,String messName,String customerName,String email) {
        this.foodName = foodName;
        this.total = total;
        this.quantity = quantity;
        this.messName=messName;
        CustomerName = customerName;
        Email =email;
    }


    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

   public Integer getTotal() {
       return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
