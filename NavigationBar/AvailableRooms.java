package com.thiman.android.reservationmanager.NavigationBar;

import android.animation.ValueAnimator;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.thiman.android.reservationmanager.AvailableDetails.AvailableRoomDetailsModel;
import com.thiman.android.reservationmanager.AvailableDetails.RecycleViewAvailableAdapter;
import com.thiman.android.reservationmanager.R;
import com.thiman.android.reservationmanager.rest.ApiClient;
import com.thiman.android.reservationmanager.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableRooms extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    RecycleViewAvailableAdapter recyclerviewAdapter;


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

        final TextView first = findViewById(R.id.first);
        final TextView second = findViewById(R.id.second);
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = first.getWidth();
                final float translationX = width * progress;
                first.setTranslationX(translationX);
//                second.setTranslationX(translationX - width);
            }
        });
        animator.start();

        recyclerView = findViewById(R.id.rv_recycler_view_ar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        fetchData();



    }




    private void fetchData(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AvailableRoomDetailsModel> call = apiInterface.getAvailableDetails();
        call.enqueue(new Callback<AvailableRoomDetailsModel>() {
            @Override
            public void onResponse(Call<AvailableRoomDetailsModel> call, Response<AvailableRoomDetailsModel> response) {
                List<AvailableRoomDetailsModel.Array> model = new ArrayList<AvailableRoomDetailsModel.Array>();
                for(int i = 0; i<response.body().getData().size(); i++){
                    model.add(response.body().getData().get(i));

                }
                recyclerviewAdapter = new RecycleViewAvailableAdapter(model);
                recyclerView.setAdapter(recyclerviewAdapter);
            }

            @Override
            public void onFailure(Call<AvailableRoomDetailsModel> call, Throwable t) {
//                Log.i("Tag ", t.getMessage());
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    }



//    ArrayList<String> roomIdAr;
//    ArrayList<String> roomType;
//    ArrayList<String> packageType;
//    RecyclerView roomRecyclerAr;
//    ViewPager viewPagerBooking;
//    RecyclerView.LayoutManager roomLayoutManagerAr;
//        AvailableRoomsAdapter roomAdapterAr;
//    PagerAdapter pagerAdapterBooking;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_available_rooms);
//        Toolbar toolbar = findViewById(R.id.toolBarAr);
//        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        }
//
//        roomRecyclerAr = findViewById(R.id.rv_recycler_view_ar);
//
//        roomIdAr = new ArrayList<>();
//        roomType = new ArrayList<>();
//        packageType = new ArrayList<>();
//
//        for (int i = 0; i <= 15; i++) {
//            roomIdAr.add("j" + i);
//            roomType.add("01.20");
//            packageType.add("01.26");
//
//        }
//
//        Log.i("Tag :", "out");
//        roomRecyclerAr.setHasFixedSize(true);
//        roomLayoutManagerAr = new LinearLayoutManager(this);
//        Log.i("Tag :", "B4");
//        roomAdapterAr = new AvailableRoomsAdapter(roomIdAr, roomType, packageType);
//        Log.i("Tag :", "Adapter");
//        roomRecyclerAr.setLayoutManager(roomLayoutManagerAr);
//        roomRecyclerAr.setAdapter(roomAdapterAr);
//
//    }

