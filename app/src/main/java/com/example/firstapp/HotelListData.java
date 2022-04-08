package com.example.firstapp;

import com.google.gson.annotations.SerializedName;

//model
public class HotelListData {

    @SerializedName("name")
    String hotel_name;
    String price;
    @SerializedName("available")
    String availability;

    public HotelListData(String hotel_name, String price, String availability) {
        this.hotel_name = hotel_name;
        this.price = price;
        this.availability = availability;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
