package com.example.liew.idelivery.Model;

/**
 * Created by kundan on 12/15/2017.
 */

public class User {

    private String Name,Password,Phone,IsStaff,secureCode;

    public User() {
    }

    public User(String name, String password, String secureCode) {
        Name = name;
        Password = password;
        this.secureCode = secureCode;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }
}