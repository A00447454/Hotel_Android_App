package com.example.firstapp;

import com.google.gson.annotations.SerializedName;

public class GuestDetails {

    @SerializedName("guest_name")
    String guestName;
    String gender;

    public GuestDetails(String guestName, String gender) {
        this.guestName = guestName;
        this.gender = gender;
    }
}
