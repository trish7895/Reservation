package com.thiman.android.reservationmanager.NavigationBar;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.thiman.android.reservationmanager.AvailableDetails.RecycleViewAvailableAdapter;
import com.thiman.android.reservationmanager.BookingDetails.BookingDetailsModel;
import com.thiman.android.reservationmanager.BookingDetails.RecycleViewBookingAdapter;
import com.thiman.android.reservationmanager.Home;
import com.thiman.android.reservationmanager.MainActivity;
import com.thiman.android.reservationmanager.Model.BookingDetails;
import com.thiman.android.reservationmanager.R;
import com.thiman.android.reservationmanager.rest.ApiClient;
import com.thiman.android.reservationmanager.rest.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Bookings extends AppCompatActivity {

    RecyclerView recyclerView;
    ApiInterface apiInterface;
    RecycleViewBookingAdapter recyclerviewAdapter;
    Animation blink;
    ImageView ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        blink = AnimationUtils.loadAnimation(this,R.anim.blink);

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

//        viewPagerBooking = (ViewPager) findViewById(R.id.viewpager);
        recyclerView = findViewById(R.id.rv_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        fetchData();



    }


    private void fetchData(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<BookingDetailsModel> call = apiInterface.getBookingDetails();
        call.enqueue(new Callback<BookingDetailsModel>() {
            @Override
            public void onResponse(Call<BookingDetailsModel> call, Response<BookingDetailsModel> response) {
                Log.i("Tag ", String.valueOf(response.code()));
                if(response.code()==200){
                    List<BookingDetailsModel.Array> model = new ArrayList<BookingDetailsModel.Array>();
                    for(int i = 0; i<response.body().getData().size(); i++){
                        model.add(response.body().getData().get(i));

                    }
                    recyclerviewAdapter = new RecycleViewBookingAdapter(model);
                    recyclerView.setAdapter(recyclerviewAdapter);
                }
            }

            @Override
            public void onFailure(Call<BookingDetailsModel> call, Throwable t) {
//                Log.i("Tag ", t.getMessage());
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Bookings.this,AlertDialog.THEME_HOLO_DARK);

                builder1.setMessage("Try Again?");

                builder1.setCancelable(false);
                builder1.setTitle("Connection Error ");
                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });

                AlertDialog dialog = builder1.create();
                dialog.show();
            }
        });
    }
}