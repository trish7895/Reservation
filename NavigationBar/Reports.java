package com.thiman.android.reservationmanager.NavigationBar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.thiman.android.reservationmanager.R;
import com.thiman.android.reservationmanager.ReportCharts.ReportBar;
import com.thiman.android.reservationmanager.ReportCharts.ReportBarBooking;
import com.thiman.android.reservationmanager.ReportCharts.ReportBarCountry;
import com.thiman.android.reservationmanager.ReportCharts.ReportPieBooking;
import com.thiman.android.reservationmanager.ReportCharts.ReportsPie;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel2;
import com.thiman.android.reservationmanager.ReportDetails.ReportModel3;
import com.thiman.android.reservationmanager.rest.ApiClient;
import com.thiman.android.reservationmanager.rest.ApiInterface;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Asus on 1/30/2018.
 */

public class Reports extends AppCompatActivity {

    Animation blink;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        blink = AnimationUtils.loadAnimation(this,R.anim.blink);
        CircleMenu circleMenu = findViewById(R.id.cirMenu);
        circleMenu.setMainMenu(Color.parseColor("#ffffff"),R.drawable.combo_chart,R.drawable.combo_chart)
        .addSubMenu(Color.parseColor("#00ceff"),R.drawable.circular_chart)
                .addSubMenu(Color.parseColor("#00ceff"),R.drawable.barchart1)
                .addSubMenu(Color.parseColor("#00ceff"),R.drawable.circular_chart)
                .addSubMenu(Color.parseColor("#00ceff"),R.drawable.barchart1)
                .addSubMenu(Color.parseColor("#00ceff"),R.drawable.pie_chart)
                .addSubMenu(Color.parseColor("#00ceff"),R.drawable.bars)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {

                if (i==0){
                    fetchData(1);
                }
                if (i == 1){
                    fetchData(2);
                }
                if(i == 2){
                    fetchBookingData(1);
                }
                if(i == 3){
                    fetchBookingData(2);
                }
//                Toast.makeText(Reports.this,"Reports Through"+share[i],Toast.LENGTH_SHORT).show();

                if(i == 4){
                    fetchCountryData(1);
                }
                if(i == 5){
                    fetchCountryData(2);
                }
            }
        });
    }

    private void fetchData(final int selector){
        progressDialog = new ProgressDialog(Reports.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ReportModel> call = apiInterface.getReportData();
        call.enqueue(new Callback<ReportModel>() {
            @Override
            public void onResponse(Call<ReportModel> call, Response<ReportModel> response) {
                Log.i("Tag ", String.valueOf(response.body().getArray().size()));
                progressDialog.dismiss();
                if(response.code()==200){
                   if(selector == 1){
                       Intent book = new Intent(Reports.this, ReportsPie.class);
                       Bundle bundle = new Bundle();
                       bundle.putSerializable("Data", (Serializable) response.body().getArray());
                       book.putExtras(bundle);
                       startActivity(book);
                   }
                   if(selector == 2){
                       Intent book = new Intent(Reports.this, ReportBar.class);
                       Bundle bundle = new Bundle();
                       bundle.putSerializable("Data", (Serializable) response.body().getArray());
                       book.putExtras(bundle);
                       startActivity(book);
                   }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ReportModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void fetchBookingData(final int selector){
        progressDialog = new ProgressDialog(Reports.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ReportModel2> call = apiInterface.getBookingReportData();
        call.enqueue(new Callback<ReportModel2>() {
            @Override
            public void onResponse(Call<ReportModel2> call, Response<ReportModel2> response) {
                progressDialog.dismiss();
                if(response.code()==200){
                    if(selector == 1){
                        Intent book = new Intent(Reports.this, ReportPieBooking.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Data", (Serializable) response.body().getArray());
                        book.putExtras(bundle);
                        startActivity(book);
                    }
                    if(selector == 2){
                        Intent book = new Intent(Reports.this, ReportBarBooking.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Data", (Serializable) response.body().getArray());
                        book.putExtras(bundle);
                        startActivity(book);
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ReportModel2> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void fetchCountryData(final int selector){
        progressDialog = new ProgressDialog(Reports.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ReportModel3> call = apiInterface.getReportCountryData();
        call.enqueue(new Callback<ReportModel3>() {
            @Override
            public void onResponse(Call<ReportModel3> call, Response<ReportModel3> response) {
                Log.i("Tag ", String.valueOf(response.body().getArray().size()));
                progressDialog.dismiss();
                if(response.code()==200){
                    if(selector == 1){
                        Intent country = new Intent(Reports.this, ReportsPie.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Data", (Serializable) response.body().getArray());
                        country.putExtras(bundle);
                        startActivity(country);
                    }
                    if(selector == 2){
                        Intent country = new Intent(Reports.this, ReportBarCountry.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Data", (Serializable) response.body().getArray());
                        country.putExtras(bundle);
                        startActivity(country);
                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ReportModel3> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
