package com.example.mini.Model;

public class Users {

    private String Username;
    private String Email;
    private String Mobileno;
    private String Address;
    private String Password;

    public Users() {
    }

    public Users(String username, String e_mail, String mobileno, String address, String password) {
        Username = username;
        Email = e_mail;
        Mobileno = mobileno;
        Address = address;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String e_mail) {
        Email = e_mail;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
