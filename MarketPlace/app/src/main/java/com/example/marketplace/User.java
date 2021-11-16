package com.example.marketplace;

public class User {

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String telephone;
    private String fullname;

    public User(){
    }

    public User(String name, String lastname, String email, String password, String telephone) {
        this.setName(name);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setPassword(password);
        this.setTelephone(telephone);
        this.setFullname(this.name + " " + this.lastname);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
