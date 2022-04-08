package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HotelListFragment extends Fragment implements ItemClickListener {

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<com.example.firstapp.HotelListData> userListResponseData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String guestCount = getArguments().getString("number of guests");
        String guestName = getArguments().getString("name of guest");

        //Set up the header
        headingTextView.setText(new StringBuilder().append("Welcome ").append(guestName).append(", displaying hotel for ").append(guestCount).append(" guests staying from ").append(checkInDate).append(" to ").append(checkOutDate).toString());
//         //Set up the RecyclerView
//        ArrayList<HotelListData> hotelListData = initHotelListData();
//        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListData);
//        recyclerView.setAdapter(hotelListAdapter);
            getHotelListData();

    }
    public ArrayList<HotelListData> initHotelListData() {
        ArrayList<HotelListData> list = new ArrayList<>();

        list.add(new HotelListData("Hotel A", "1000$", "available"));
        list.add(new HotelListData("Taj", "200$", "unavailable"));
        list.add(new HotelListData("Hotel B", "700$", "available"));
        list.add(new HotelListData("C", "250$", "available"));
        list.add(new HotelListData("Hotel D", "2000$", "unavailable"));
        list.add(new HotelListData("Hotel Pearl", "1500$", "available"));
        list.add(new HotelListData("Hotel F", "600$", "unavailable"));
        list.add(new HotelListData("Viva", "750$", "unavailable"));
        list.add(new HotelListData("Rock", "1000$", "unavailable"));
        list.add(new HotelListData("Hotel S", "1450$", "available"));
        list.add(new HotelListData("R", "2100$", "available"));
        list.add(new HotelListData("M", "1200$", "available"));

        return list;
    }

    private void getHotelListData() {
        progressBar.setVisibility(View.VISIBLE);
        Api.getClient().getHotelsList(new Callback<HotelList>() {
            @Override
            public void success(HotelList hotelList, Response response) {
                userListResponseData = hotelList.getHotelListDataList();
                setupRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
//        com.example.firstapp.Api.getClient().getHotelsList(new Callback<List<HotelListData>>() {
//            @Override
//            public void success(List<HotelListData> hotelListData, Response response) {
//                userListResponseData = userListResponseData;
//
//                setupRecyclerView();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), userListResponseData);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }


    @Override
    public void onClick(View view, int position) {
        HotelListData hotelListData = userListResponseData.get(position);

        String hotelName = hotelListData.getHotel_name();
        String price = hotelListData.getPrice();
        String availability = hotelListData.getAvailability();

        Bundle bundle = new Bundle();
        bundle.putString("hotel name", hotelName);
        bundle.putString("hotel price", price);
        bundle.putString("hotel availability", availability);

        GuestDetailsFragment GuestDetailsFragment = new GuestDetailsFragment();
        GuestDetailsFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(HotelListFragment.this);
        fragmentTransaction.replace(R.id.main_layout, GuestDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }
}
