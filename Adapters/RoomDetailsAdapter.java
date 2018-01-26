package com.thiman.android.reservationmanager.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thiman.android.reservationmanager.R;

/**
 * Created by Asus on 1/25/2018.
 */

public class RoomDetailsAdapter extends RecyclerView.Adapter<RoomDetailsAdapter.MyViewHolder> {

    private String[] mDataset;
    private String[] noOfFloor;
    private String[] price;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView roomId;
        public TextView noOfRoom;
        public TextView roomPrice;

        public MyViewHolder(View v) {
            super(v);

            mCardView = v.findViewById(R.id.card_view_rd);
            roomId = v.findViewById(R.id.tv_text_rnrd);
            noOfRoom = v.findViewById(R.id.tv_frd);
            roomPrice = v.findViewById((R.id.tv_pt_rd));

        }

    }

    public RoomDetailsAdapter(String[] myDataset, String[] noOfFloor,String[] price){
        this.noOfFloor = noOfFloor;
        this.price = price;
        mDataset = myDataset;
    }

    @Override
    public RoomDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_details_card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.roomId.setText(mDataset[position]);
        holder.noOfRoom.setText(noOfFloor[position]);
        holder.roomPrice.setText(price[position]);

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }





}