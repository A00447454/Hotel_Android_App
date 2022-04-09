package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    int guestCount;
    TextView GuestNoTextView;

    GuestListAdapter(Context context, int guestCount) {
        this.layoutInflater = LayoutInflater.from(context);
        this.guestCount = guestCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.GuestNoTextView.setText("Guest "+ (position + 1));
//        String hotelName = hotelListData.get(position).getHotel_name();
//        String hotelPrice = hotelListData.get(position).getPrice();
//        String hotelAvailability = hotelListData.get(position).getAvailability();
//
//        // set up the text
//        holder.hotelName.setText(hotelName);
//        holder.hotelAvailability.setText(hotelAvailability);
//        holder.hotelPrice.setText(hotelPrice);
    }

    @Override
    public int getItemCount() {
            return guestCount;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView GuestNoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            GuestNoTextView = itemView.findViewById(R.id.guest_no_text_view);
//            hotelName = itemView.findViewById(R.id.hotel_name_text_view);
//            hotelPrice = itemView.findViewById(R.id.price_text_view);
//            hotelAvailability = itemView.findViewById(R.id.availability_text_view);
//
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (clickListener != null)
//                clickListener.onClick(view, getAbsoluteAdapterPosition());
//        }
    }

}