package com.thiman.android.reservationmanager.NavigationBar;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thiman.android.reservationmanager.Adapters.BookingAdapter;
import com.thiman.android.reservationmanager.R;

import java.util.ArrayList;

public class Bookings extends AppCompatActivity {

    ArrayList<String> roomId;
    ArrayList<String> checkinDate;
    ArrayList<String> checkoutDate;
    RecyclerView roomRecycler;
    ViewPager viewPagerBooking;
    RecyclerView.LayoutManager roomLayoutManager;
    RecyclerView.Adapter roomAdapter;
    PagerAdapter pagerAdapterBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


//        viewPagerBooking = (ViewPager) findViewById(R.id.viewpager);
        roomRecycler = findViewById(R.id.rv_recycler_view);

        roomId = new ArrayList<>();
        checkinDate = new ArrayList<>();
        checkoutDate = new ArrayList<>();

        for(int i = 0; i<=10;i++) {
            roomId.add("i"+i);
            checkinDate.add("01.20");
            checkoutDate.add("01.26");

        }
        roomRecycler.setHasFixedSize(true);
        roomLayoutManager = new LinearLayoutManager(this);
        roomAdapter = new BookingAdapter(roomId,checkinDate,checkoutDate);
        roomRecycler.setLayoutManager(roomLayoutManager);
        roomRecycler.setAdapter(roomAdapter);





    }
}
