package com.example.mess.Model;

public class Orderstatus {
  private String Fname;
  private String Messname;
  private  Integer Quantity;
  private Integer Total;
  private String Status;
  private String Time;
  private String CustomerName;
    private String Email;

    public Orderstatus() {
    }


    public Orderstatus(String fname, String messname, Integer quantity, Integer total, String status, String time,String customerName,String email) {
        Fname = fname;
        Messname = messname;
        Quantity = quantity;
        Total = total;
        Status = status;
        Time=time;
        CustomerName = customerName;
        Email =email;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getMessname() {
        return Messname;
    }

    public void setMessname(String messname) {
        Messname = messname;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        Total = total;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
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

