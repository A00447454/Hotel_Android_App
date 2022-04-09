package com.example.firstapp;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {
    @GET("/getListOfHotels/")
    public void getHotelsList(Callback<HotelList> callback);

    @POST("/reservationConfirmation/")
    public void bookReservation(@Body Reservation reservation, Callback<ReservationResponse> callback);
}
