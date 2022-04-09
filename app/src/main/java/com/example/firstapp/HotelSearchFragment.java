package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    //Global Variables
    View view;
    ConstraintLayout mainLayout;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText nameEditText, guestsCountEditText;
    Button confirmSearchButton, searchButton, retrieveButton, clearButton;
    DatePicker checkInDatePicker, checkOutDatePicker;
    String checkInDate, checkOutDate, guestCount, guestName;

    // Declaration of shared preferences keys
    SharedPreferences sharedPreferences;
    public static final String myPreference = "myPref";
    public static final String name = "nameKey";
    public static final String count = "guestsCount";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainLayout =view.findViewById(R.id.main_layout) ;
        titleTextView = view.findViewById(R.id.title_text_view);
        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text);
        searchTextConfirmationTextView = view.findViewById(R.id.search_confirm_text_view);

        //For Shared Pref
        nameEditText = view.findViewById(R.id.name_edit_text);
        retrieveButton = view.findViewById(R.id.retrieve_button);
        clearButton = view.findViewById(R.id.clear_button);

        confirmSearchButton = view.findViewById(R.id.confirm_my_search_button);
        searchButton = view.findViewById(R.id.search_button);

        checkInDatePicker = view.findViewById(R.id.check_in_date_picker_view);
        checkOutDatePicker = view.findViewById(R.id.check_out_date_picker_view);

        //title text
        titleTextView.setText(R.string.welcome_text);

        // confirm text click listener
        confirmSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInDate = getDateFromCalender(checkInDatePicker);
                checkOutDate = getDateFromCalender(checkOutDatePicker);

                //Get input
                guestCount = guestsCountEditText.getText().toString();
                guestName= nameEditText.getText().toString();


                // Saving into shared preferences
                sharedPreferences = getActivity().getSharedPreferences(myPreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(name, guestName);
                editor.putString(count, guestCount);
                editor.commit();


                searchTextConfirmationTextView.setText("Dear "+ guestName +", Your check in date is " + checkInDate + " and " +
                        "your checkout date is " + checkOutDate + ". The number of guests are " + guestCount);


            }
        });

        //Search Button click Listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInDate = getDateFromCalender(checkInDatePicker);
                checkOutDate = getDateFromCalender(checkOutDatePicker);

                //Get input
                guestCount = guestsCountEditText.getText().toString();
                guestName= nameEditText.getText().toString();

                try{
                    Integer.valueOf(guestCount);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Please enter a number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putString("check in date", checkInDate);
                bundle.putString("check out date", checkOutDate);
                bundle.putString ("number of guests", guestCount);
                bundle.putString("name of guest", guestName);


                // set Fragment class Arguments
                HotelListFragment hotelsListFragment = new HotelListFragment();
                hotelsListFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
                fragmentTransaction.remove(HotelSearchFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Clear Button Click Listener
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guestsCountEditText.setText("");
                nameEditText.setText("");
            }
        });
    }



    private String getDateFromCalender(DatePicker datePicker)
        {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calender = Calendar.getInstance();

        calender.set(year,month,day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(calender.getTime());

        }

}
