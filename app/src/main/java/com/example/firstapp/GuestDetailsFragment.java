package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class GuestDetailsFragment extends Fragment {

    EditText guestNameEditText;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button bookButton;


    View view;

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        TextView checkInTextView = view.findViewById(R.id.check_in_text_view);
        TextView checkOutTextView = view.findViewById(R.id.check_out_text_view);
        TextView priceTextView = view.findViewById(R.id.price_text_view);
        TextView hotelNameTextView =  view.findViewById(R.id.hotel_name_text_view);

        String hotelName = getArguments().getString("hotel name");
        String hotelPrice = getArguments().getString("hotel price");
        String hotelAvailability = getArguments().getString("hotel availability");
        String checkInDate = getArguments().getString("check in date");
        String checkOutDate = getArguments().getString("check out date");
        String guestName = getArguments().getString("name of guest");
        String guestCount =getArguments().getString("number of guests");
        int noOfGuest = Integer.valueOf(guestCount);

        hotelNameTextView.setText(hotelName);
        checkInTextView.setText(checkInDate);
        checkOutTextView.setText(checkOutDate);
        priceTextView.setText("$ "+hotelPrice);



        hotelRecapTextView.setText(hotelName+ " availability is: " +hotelAvailability);
        setupRecyclerView(Integer.valueOf(guestCount));

        RecyclerView guestDetailsRecyclerView = view.findViewById(R.id.guest_list_recycler_view);
        Button bookButton = view.findViewById(R.id.book_button);;
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<GuestDetails> guestDetailsList = new ArrayList<GuestDetails>();
                for (int i = 0; i < noOfGuest; i++) {
                    View guestDetailsView = guestDetailsRecyclerView.getChildAt(i);

                    EditText guestNameEditText = guestDetailsView.findViewById(R.id.guest_name_edit_text);
                    RadioGroup radioGroup = guestDetailsView.findViewById(R.id.group_radio);
                    RadioButton genderRadioButton= guestDetailsView.findViewById(radioGroup.getCheckedRadioButtonId());

                    String guestName = guestNameEditText.getText().toString();
                    String gender = genderRadioButton.getText().toString();

                    GuestDetails guestDetails = new GuestDetails(guestName, gender);
                    guestDetailsList.add(guestDetails);
                }
                Reservation reservation = new Reservation(hotelName, checkInDate, checkOutDate, guestDetailsList);

                Api.getClient().bookReservation(reservation, new Callback<ReservationResponse>() {
                    @Override
                    public void success(ReservationResponse reservationResponse, Response response) {
                        String reservationId = reservationResponse.getConfirmationNumber();
                        Toast.makeText(getActivity(), reservationId, Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putString("reservation id", reservationId);

                        ReservationConfirmFragment reservationConfirmFragment = new ReservationConfirmFragment();
                        reservationConfirmFragment.setArguments(bundle);

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_layout, reservationConfirmFragment);
                        fragmentTransaction.remove(GuestDetailsFragment.this);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setupRecyclerView(int guestCount) {
        RecyclerView recyclerView = view.findViewById(R.id.guest_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        GuestListAdapter guestListAdapter = new GuestListAdapter(getActivity(), guestCount);
        recyclerView.setAdapter(guestListAdapter);
    }


}
