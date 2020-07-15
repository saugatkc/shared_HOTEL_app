package com.saugat.bag_packer.model;

public class User {
    private  String _id;
    private String fullname;
    private String username;
    private  String password;
    private String phone;
    private  String email;
    private  String image;
    private Hotel saved;

    public Hotel getSaved() {
        return saved;
    }

    public void setSaved(Hotel saved) {
        this.saved = saved;
    }

    public User(String _id) {
        this._id = _id;
    }

    public User(String fullname, String username, String password, String phone, String email, String image) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    public User (String phone, String email){
        this.phone = phone;
        this.email = email;
    }



    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

}
