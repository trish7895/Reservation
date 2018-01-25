package com.thiman.android.reservationmanager.NavigationBar;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.thiman.android.reservationmanager.Adapters.AvailableRoomsAdapter;
import com.thiman.android.reservationmanager.R;

import java.util.ArrayList;

public class AvailableRooms extends AppCompatActivity {

    ArrayList<String> roomIdAr;
    ArrayList<String> roomType;
    ArrayList<String> packageType;
    RecyclerView roomRecyclerAr;
    ViewPager viewPagerBooking;
    RecyclerView.LayoutManager roomLayoutManagerAr;
        AvailableRoomsAdapter roomAdapterAr;
    PagerAdapter pagerAdapterBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_rooms);
        Toolbar toolbar = findViewById(R.id.toolBarAr);
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }

        roomRecyclerAr = findViewById(R.id.rv_recycler_view_ar);

        roomIdAr = new ArrayList<>();
        roomType = new ArrayList<>();
        packageType = new ArrayList<>();

        for (int i = 0; i <= 15; i++) {
            roomIdAr.add("j" + i);
            roomType.add("01.20");
            packageType.add("01.26");

        }

        Log.i("Tag :", "out");
        roomRecyclerAr.setHasFixedSize(true);
        roomLayoutManagerAr = new LinearLayoutManager(this);
        Log.i("Tag :", "B4");
        roomAdapterAr = new AvailableRoomsAdapter(roomIdAr, roomType, packageType);
        Log.i("Tag :", "Adapter");
        roomRecyclerAr.setLayoutManager(roomLayoutManagerAr);
        roomRecyclerAr.setAdapter(roomAdapterAr);

    }
}
