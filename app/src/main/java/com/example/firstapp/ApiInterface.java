package com.example.firstapp;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface ApiInterface {
    @GET("/getListOfHotels/")
    public void getHotelsList(Callback<HotelList> callback);
}
