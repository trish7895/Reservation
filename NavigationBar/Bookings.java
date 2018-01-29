package com.thiman.android.reservationmanager.NavigationBar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.thiman.android.reservationmanager.Adapters.BookingAdapter;
import com.thiman.android.reservationmanager.Home;
import com.thiman.android.reservationmanager.MainActivity;
import com.thiman.android.reservationmanager.Model.BookingDetails;
import com.thiman.android.reservationmanager.R;

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

public class Bookings extends AppCompatActivity {

    ArrayList<String> roomId;
    ArrayList<String> checkinDate;
    ArrayList<String> checkoutDate;
//    List<BookingDetails> bookingDetails;
    ArrayList<BookingDetails> bookingDetails;
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
        bookingDetails = new ArrayList<>();

        roomRecycler = findViewById(R.id.rv_recycler_view);
        roomRecycler.setHasFixedSize(true);
        roomLayoutManager = new LinearLayoutManager(getApplicationContext());

        roomRecycler.setLayoutManager(roomLayoutManager);
        MyAsync myAsync = new MyAsync();
        myAsync.execute();

//        roomId = new ArrayList<>();
//        checkinDate = new ArrayList<>();
//        checkoutDate = new ArrayList<>();

//        for(int i = 0; i<=10;i++) {
//            roomId.add("i"+i);
//            checkinDate.add("01.20");
//            checkoutDate.add("01.26");
//
//        }
//        roomRecycler.setHasFixedSize(true);
//        roomLayoutManager = new LinearLayoutManager(this);
//        roomAdapter = new BookingAdapter(bookingDetails);
//        roomRecycler.setLayoutManager(roomLayoutManager);
//        roomRecycler.setAdapter(roomAdapter);





    }

    ///asynctask class
    class MyAsync extends AsyncTask<String,Integer,String> {

        @Override
        protected void onProgressUpdate(Integer... params) {
            //super.onProgressUpdate(values);
            Log.d("progres--->", String.valueOf(params[0]));
        }

        @Override
        protected String doInBackground(String... params) {


            Log.d("starting  ","doing background");
            String response=null;


            try{
                SharedPreferences reservationSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor reservationSettingsEditor = reservationSettings.edit();
                String accessToken=reservationSettings.getString("access_token","token is empty");
                Log.d("my current token",accessToken);

                URL loginURL = new URL("http://10.0.2.2:3002/viewAllBandRRooms");
                HttpURLConnection connection = (HttpURLConnection) loginURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("x-access-token",accessToken);
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();


                int statusCode = connection.getResponseCode();
                if(statusCode == 200) {
                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    int ch;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((ch = inputStream.read()) != -1) {

                        stringBuffer.append((char) ch);
                       // publishProgress(progress);
                    }
                    // loginResponse = new Response(ResponseCode.CODE_OK, stringBuffer.toString());
                    response=stringBuffer.toString();
                    System.out.println("this is my response : "+response);
                } else {
                    System.out.println("this is my response code : "+statusCode);
                    JSONObject error_response =new JSONObject();
                    try {
                        error_response.put("status",String.valueOf(statusCode));
                        error_response.put("message","failed to load booking details!");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return error_response.toString();
                    //  loginResponse = new Response(ResponseCode.CODE_FAILED, connection.getResponseMessage());
                }
                connection.disconnect();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {

//            try {
//                System.out.println("this is my response : "+s);
//                JSONObject response = new JSONObject(s);
//
//                int statuscode = response.getInt("status");
//                if(statuscode==200){
//                           JSONArray bookingDetailsJson= response.getJSONArray("data");
//                    for (int i = 0; i < bookingDetailsJson.length();i++)
//                    {
//                        JSONObject o = bookingDetailsJson.getJSONObject(i);
//
//                    //    System.out.println("bookingDetails :"+bookingDetailsJson.get(i).toString());
//                        BookingAdapter bookingDetail = new BookingAdapter(o.getString("roomId")),
//                                o.getString("checkInDate"),o.getString("checkOutDate"));
//                        bookingDetails.add(bookingDetail);
//                    }
//                    roomAdapter = new BookingAdapter(Bookings.this,bookingDetails);
//                    roomRecycler.setAdapter(roomAdapter);
//
//                }else{
//
//                    Log.d("status",response.getString("status"));
//                    Toast.makeText(getApplicationContext(),response.get("message").toString(), Toast.LENGTH_LONG).show();
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            // super.onPostExecute(s);
        }
    }
}
