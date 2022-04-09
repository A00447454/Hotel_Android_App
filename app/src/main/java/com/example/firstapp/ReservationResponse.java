package com.example.firstapp;

import com.google.gson.annotations.SerializedName;

public class ReservationResponse {

    @SerializedName("confirmation_number")
    String confirmationNumber;

    public String getConfirmationNumber() {
        return confirmationNumber;
    }
}
