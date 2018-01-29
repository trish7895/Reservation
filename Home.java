package com.thiman.android.reservationmanager;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.DatePickerDialog;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.thiman.android.reservationmanager.Fragments.SelectPackageType;
import com.thiman.android.reservationmanager.Fragments.SelectRoomTypeFragment;
import com.thiman.android.reservationmanager.NavigationBar.AboutUs;
import com.thiman.android.reservationmanager.NavigationBar.AvailableRooms;
import com.thiman.android.reservationmanager.NavigationBar.Bookings;
import com.thiman.android.reservationmanager.NavigationBar.Currency;
import com.thiman.android.reservationmanager.NavigationBar.Help;
import com.thiman.android.reservationmanager.NavigationBar.Language;
import com.thiman.android.reservationmanager.NavigationBar.Promotions;
import com.thiman.android.reservationmanager.NavigationBar.Reports;
import com.thiman.android.reservationmanager.NavigationBar.RoomDetails;
import com.thiman.android.reservationmanager.NavigationBar.Settings;
import com.thiman.android.reservationmanager.NavigationBar.Share;
import com.thiman.android.reservationmanager.Util.HttpCall;

import org.json.JSONObject;

import static java.lang.System.out;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button checkIn;
    Button checkOut;
    Button search,rincre,rdecre,aincre,adecre,cincre,cdecre;
    DatePickerDialog picker;
    TextView tvci,tvco,noroom,noadult,nochild,noroom1,noadult1,nochild1,type;
    int room = 1,adult = 1,child = 1;
    Animation fadeIn;
    RelativeLayout rl1;
    LinearLayout ll2;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar mToolbar;
    String roomCount, adultCount, childrenCount;
    private CheckBox cb1, cb2, cb3, cb4,cb5,cb6,cb7,cb8,cb9;
    Spinner rTypeSpinner,pTypeSpinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rTypeSpinner = findViewById(R.id.rType);
        pTypeSpinner = findViewById(R.id.pType);


        final List<String> rSpinner= new ArrayList<String>();
        final List<String> pSpinner= new ArrayList<String>();

        rSpinner.add("Single");
        rSpinner.add("Double");
        rSpinner.add("Double Double");
        rSpinner.add("Cabana");
        rSpinner.add("Twin");
        rSpinner.add("Parlor");

        pSpinner.add("Normal");
        pSpinner.add("Gold");
        pSpinner.add("Silver");
        pSpinner.add("Platinum");

        ArrayAdapter spinnerAdapter = new ArrayAdapter(Home.this,R.layout.support_simple_spinner_dropdown_item,rSpinner);
        ArrayAdapter spinnerAdapterP = new ArrayAdapter(Home.this,R.layout.support_simple_spinner_dropdown_item,pSpinner);

        rTypeSpinner.setAdapter(spinnerAdapter);
        pTypeSpinner.setAdapter(spinnerAdapterP);


     rTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             Toast.makeText(Home.this,rSpinner.get(i),Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });

        pTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Home.this,pSpinner.get(i),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mOrderReceiver,
                new IntentFilter("order"));


        noroom = findViewById(R.id.room);
        noadult = findViewById(R.id.adult);
        nochild = findViewById(R.id.child);
        search = findViewById(R.id.search1);


         mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.imageanim);
        rl1 = findViewById(R.id.home);
        rl1.setAnimation(fadeIn);

        checkIn = findViewById(R.id.buttonci);
        checkOut = findViewById(R.id.buttonco);




//        noroom1 = (TextView)findViewById(R.id.room);
//        noadult1 = (TextView)findViewById(R.id.adult);
//        nochild1 = (TextView)findViewById(R.id.child);
//        rincre = (Button)findViewById(R.id.increr) ;




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setAnimation(fadeIn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
                intent.putExtra("sms_body", "Your Reservation has been Confirmed.Contact Us for more details.Thank You");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                }
            }
        });


         drawer = findViewById(R.id.drawer_layout);
        drawer.setScrimColor(Color.parseColor("#33000000"));
//        drawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
         toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // set up action bar and toggle button

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvci= findViewById(R.id.chkin);
        tvco= findViewById(R.id.chkout);





//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cb1 = findViewById(R.id.c1);
//                cb2 = findViewById(R.id.c2);
//                cb3 = findViewById(R.id.c3);
//
//                cb4 = findViewById(R.id.c4);
//                cb5 = findViewById(R.id.c5);
//                cb6 = findViewById(R.id.c6);
//
//                cb7 = findViewById(R.id.c7);
//                cb8 = findViewById(R.id.c8);
//                cb9 = findViewById(R.id.c9);
//                String roomType="";
//                if(cb1.isChecked()){
//                    roomType=cb1.getText().toString();
//                }
//                String noRoom=noroom.toString();
//                String noAdult=noadult.toString();
//                String noChild=nochild.toString();
//              //  cb10 = findViewById(R.id.c10);
//                MyAsync myAsync= new MyAsync();
//                myAsync.execute("adminnew","123456");
//
//
//            }
//        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

//        if(toggle.onOptionsItemSelected(item)){
//            return true;
//
//        }
//        return super.onOptionsItemSelected(item);
        int id = item.getItemId();
            if(toggle.onOptionsItemSelected(item)){
                return true;
            }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


            if (id == R.id.nav_booking) {
                // Handle the camera action
               Intent booking = new Intent(Home.this,Bookings.class);
                startActivity(booking);


            } else if (id == R.id.nav_rooms) {

                Intent availableRooms = new Intent(Home.this,AvailableRooms.class);
                startActivity(availableRooms);


           } else if (id == R.id.nav_RoomDe) {

                Intent roomDetails = new Intent(Home.this,RoomDetails.class);
                startActivity(roomDetails);

            } else if (id == R.id.nav_promo) {
                Intent promotions = new Intent(Home.this,Promotions.class);
                startActivity(promotions);

            } else if (id == R.id.nav_reports) {
                Intent reports = new Intent(Home.this,Reports.class);
                startActivity(reports);

            } else if (id == R.id.nav_currency) {

                Intent currency = new Intent(Home.this,Currency.class);
                startActivity(currency);
            } else if (id == R.id.nav_settings) {
                Intent settings = new Intent(Home.this,Settings.class);
                startActivity(settings);

            } else if (id == R.id.nav_language) {
                Intent lang = new Intent(Home.this,Language.class);
                startActivity(lang);

            } else if (id == R.id.nav_help) {
                Intent help = new Intent(Home.this,Help.class);
                startActivity(help);

            } else if (id == R.id.nav_send) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "Confirming Bookings");
                intent.putExtra(Intent.EXTRA_TEXT, "Your Reservation has been confirmed.Contact us for more Details.");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            } else if (id == R.id.nav_about) {
                Intent about = new Intent(Home.this,AboutUs.class);
                startActivity(about);

            } else if (id == R.id.nav_share) {
                Intent share = new Intent(Home.this,Share.class);
                startActivity(share);

        } else if (id == R.id.nav_logout) {


            }



            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;



    }


        public void chkIn(View v) {
            SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        final Calendar cldr1 = Calendar.getInstance();
         int dayci = cldr1.get(Calendar.DAY_OF_MONTH);
         int month = cldr1.get(Calendar.MONTH);
        int year = cldr1.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(Home.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Log.i("check","0");
                            tvci.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            if((dayOfMonth == 31) && ((monthOfYear+1)==1))
                        tvco.setText("1" + "-" + ((monthOfYear + 1)+1) + "-" + year);


                        tvco.setText(dayOfMonth + 1 + "-" + (monthOfYear + 1) + "-" + year);


                    }
                }, year, month, dayci);

        //Get the DatePicker instance from DatePickerDialog
        DatePicker dp = picker.getDatePicker();
        //Set the DatePicker minimum date selection to current date
        dp.setMinDate(cldr1.getTimeInMillis());//get the current day
        //dp.setMinDate(System.currentTimeMillis() - 1000);// Alternate way to get the current day

        //Add 6 days with current date
       cldr1.add(Calendar.DAY_OF_MONTH,10000);

        //Set the maximum date to select from DatePickerDialog
        dp.setMaxDate(cldr1.getTimeInMillis());
        //Now DatePickerDialog have 7 days range to get selection any one from those dates
        picker.show();

    }

    public void chkout(final View v) {

        final Calendar cldr = Calendar.getInstance();
        final int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(Home.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                        tvco.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


                        if (tvci.getText().equals(tvco.getText())){
//                            Toast.makeText(Home.this, "Check CheckOut Date Again...!!!", Toast.LENGTH_SHORT).show();
//                        }
//                        else if( ){

                            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this,AlertDialog.THEME_HOLO_DARK);

                            // Set a message/question for alert dialog
                            builder.setMessage("Want to change the Check Out Date?");

                            // Specify the dialog is not cancelable
                            builder.setCancelable(false);

                            // Set a title for alert dialog
                            builder.setTitle("Error Check Out");
                            // Set the positive/yes button click click listener
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


//                                 if(dayOfMonth == 31 && ((monthOfYear == 1)||(monthOfYear == 3)||(monthOfYear == 5)||(monthOfYear == 7)||(monthOfYear == 8)||(monthOfYear == 10)||(monthOfYear == 12))) {
////                                        // Do something when click positive button
                                        tvco.setText(dayOfMonth + 1 + "-" + (monthOfYear + 1) + "-" + year);
//
//                                   }

                                }
                            });

                            AlertDialog dialog = builder.create();
                            // Display the alert dialog on interface
                            dialog.show();

//
                        }
                    }
                }, year, month, day);
        //Get the DatePicker instance from DatePickerDialog
        DatePicker dp = picker.getDatePicker();
        //Set the DatePicker minimum date selection to current date
        dp.setMinDate(cldr.getTimeInMillis());//get the current day
        //dp.setMinDate(System.currentTimeMillis() - 1000);// Alternate way to get the current day

        //Add 6 days with current date
        cldr.add(Calendar.DAY_OF_MONTH,10000);

        //Set the maximum date to select from DatePickerDialog
        dp.setMaxDate(cldr.getTimeInMillis());
        //Now DatePickerDialog have 7 days range to get selection any one from those dates

        picker.show();

    }



    public void select(View view){

        SelectRoomTypeFragment commentFragment = new SelectRoomTypeFragment();
        commentFragment.show(getSupportFragmentManager(),"Select ...");

   }


    public BroadcastReceiver mOrderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            roomCount  =  intent.getStringExtra("roomCount");
            adultCount  =  intent.getStringExtra("adultCount");
            childrenCount  =  intent.getStringExtra("childrenCount");

            noroom.setText(roomCount);
            noadult.setText(adultCount);
            nochild.setText(childrenCount);


            Toast.makeText(getApplicationContext(), roomCount + " " + adultCount + " " + childrenCount,Toast.LENGTH_LONG).show();
        }
    };

      public void check(View view){
        SelectPackageType selectPackageType = new SelectPackageType();

      }

  class MyAsync extends AsyncTask<String,Integer,String>{

      @Override
      protected void onProgressUpdate(Integer... params) {
          //super.onProgressUpdate(values);
          Log.d("progres--->", String.valueOf(params[0]));
      }

      @Override
      protected String doInBackground(String... params) {


          Log.d("starting  ","doing background");
          String response=null;
          int progress=0;

            try{
                Thread.sleep(10000);
                URL loginURL = new URL("http://10.0.2.2:3002/login");
                HttpURLConnection connection = (HttpURLConnection) loginURL.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                Map<String, String> loginMap = new HashMap<>();
                loginMap.put("username", params[0]);
                loginMap.put("password", params[1]);
                JSONObject loginDetails = new JSONObject(loginMap);

                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(loginDetails.toString());

                dataOutputStream.flush();
                dataOutputStream.close();

                int statusCode = connection.getResponseCode();
                if(statusCode == 200) {
                    InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                    int ch;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((ch = inputStream.read()) != -1) {
                        progress++;
                        stringBuffer.append((char) ch);
                        publishProgress(progress);
                    }
                   // loginResponse = new Response(ResponseCode.CODE_OK, stringBuffer.toString());
                    response=stringBuffer.toString();
                    System.out.println("this is my response : "+response);
                } else {
                    System.out.println("this is my response : "+statusCode);
                  //  loginResponse = new Response(ResponseCode.CODE_FAILED, connection.getResponseMessage());
                }
                connection.disconnect();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          return response;
      }

      @Override
      protected void onPostExecute(String s) {
          Log.d("onpostg  ",s);
          System.out.println("this is my response : "+s);
          super.onPostExecute(s);
      }
  }

}
