package com.example.firstapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reservation {

    @SerializedName("hotelname")
    String hotelName;
    @SerializedName("checkin_date")
    String checkInDate;
    @SerializedName("checkout_date")
    String checkOutDate;
    @SerializedName("guests_list")
    List<GuestDetails> guestDetailsList;

    public Reservation(String hotelName, String checkInDate, String checkOutDate, List<GuestDetails> guestDetailsList) {
        this.hotelName = hotelName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guestDetailsList = guestDetailsList;
    }
}
