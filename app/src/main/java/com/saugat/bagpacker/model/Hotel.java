package com.saugat.bagpacker.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String _id;
    private String owner;
    private String hotelname;
    private String profileimage;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String addressDistrict;
    private String addressCity;
    private Double latitude;
    private Double longitude;
    private String noOfRooms;
    private String available;
    private String price;

//    private ArrayList gallery;
//    private ArrayList features;
//
//
//    public ArrayList getGallery() {
//        return gallery;
//    }
//
//    public void setGallery(ArrayList gallery) {
//        this.gallery = gallery;
//    }
//
//    public ArrayList getFeatures() {
//        return features;
//    }
//
//    public void setFeatures(ArrayList features) {
//        this.features = features;
//    }

    public Hotel(String owner, String hotelname, String profileimage, String username, String password, String phone, String email, String addressDistrict, String addressCity, String noOfRooms, String available, String price) {
        this.owner = owner;
        this.hotelname = hotelname;
        this.profileimage = profileimage;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.addressDistrict = addressDistrict;
        this.addressCity = addressCity;
        this.noOfRooms = noOfRooms;
        this.available = available;
        this.price = price;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = _id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
