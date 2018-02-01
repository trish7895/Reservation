package com.thiman.android.reservationmanager.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.thiman.android.reservationmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectRoomTypeFragment extends DialogFragment {

    private ElegantNumberButton numberOfRooms, numberOfAdults, numberOfChildren;
    String roomCount, adultCount, childrenCount;
    private Button btnOk, btnCancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select, container, false);


        numberOfRooms = view.findViewById(R.id.numOfrooms);
        numberOfAdults = view.findViewById(R.id.numOfadults);
        numberOfChildren = view.findViewById(R.id.numOfchild);
        btnOk = view.findViewById(R.id.btnOk);

        numberOfRooms.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomCount = numberOfRooms.getNumber();
            }
        });

        numberOfAdults.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                adultCount = numberOfAdults.getNumber();
            }
        });

        numberOfChildren.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                childrenCount = numberOfChildren.getNumber();
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), roomCount + " " + adultCount + " " + childrenCount,Toast.LENGTH_LONG).show();
                Intent intent = new Intent("order");
                intent.putExtra("roomCount",roomCount);
                intent.putExtra("adultCount",adultCount);
                intent.putExtra("childrenCount",childrenCount);
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                dismiss();
            }
        });

        return view;
    }

}
