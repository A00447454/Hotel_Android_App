package com.example.firstapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelList {

    public List<HotelListData> getHotelListDataList() {
        return hotelListDataList;
    }

    @SerializedName("hotel_list")
    List<HotelListData> hotelListDataList;


}
