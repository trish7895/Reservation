package com.thiman.android.reservationmanager.RoomDetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thiman.android.reservationmanager.R;

import java.util.List;

/**
 * Created by Asus on 1/30/2018.
 */

public class RecyclerViewDetailsAdapter extends RecyclerView.Adapter<RecyclerViewDetailsAdapter.MyHolder>{

    List<RoomDetailsModel.Array> recyclerdata;


    public RecyclerViewDetailsAdapter(List<RoomDetailsModel.Array> recyclerdata) {
        this.recyclerdata = recyclerdata;
    }

    @Override
    public RecyclerViewDetailsAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_details_card_item,parent,false);
        RecyclerViewDetailsAdapter.MyHolder myHolder = new RecyclerViewDetailsAdapter.MyHolder(view);
        return myHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewDetailsAdapter.MyHolder holder, int position) {

//        RoomDetailsModel dataModel = recyclerdata.get(position);
       holder.roomNo.setText(String.valueOf(recyclerdata.get(position).getRoomId()));
       holder.location.setText(recyclerdata.get(position).getLocation());
       holder.condiation.setText(recyclerdata.get(position).getCondition());

    }

    @Override
    public int getItemCount() {
        return recyclerdata.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView roomNo, location, condiation;

        public MyHolder(View itemView) {
            super(itemView);
            roomNo = itemView.findViewById(R.id.tv_text_rnrd);
            location = itemView.findViewById(R.id.tv_frd);
            condiation = itemView.findViewById(R.id.tv_pt_rd);


        }
    }
}
