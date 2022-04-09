package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class ReservationConfirmFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.reservation_confirmation_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView reservationTextView = view.findViewById(R.id.confirmation_text_view);

//        String hotelName = getArguments().getString("hotel name");
        String reservationId = getArguments().getString("reservation id");

        reservationTextView.setText("Hotel is successfully booked! Your reservation confirmation number is: " +reservationId);
    }


}