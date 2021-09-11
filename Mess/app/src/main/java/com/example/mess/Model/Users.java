package com.example.mess.Model;

public class Users
{
    private String MessName;
    private String Messaddress;
    private String Mobileno;
    private String Email;
    private String MessType;
    private String OwnerName;

    public Users() {
    }

    public Users(String messName, String messaddress, String ownername, String mobileno, String email, String messType) {
        MessName = messName;
        Messaddress = messaddress;
        OwnerName=ownername;
        Mobileno = mobileno;
        Email = email;
        MessType = messType;

    }

    public String getMessName() {
        return MessName;
    }

    public void setMessName(String messName) {
        MessName = messName;
    }

    public String getMessaddress() {
        return Messaddress;
    }

    public void setMessaddress(String messaddress) {
        Messaddress = messaddress;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
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

    public String getMessType() {
        return MessType;
    }

    public void setMessType(String messType) {
        MessType = messType;
    }

}