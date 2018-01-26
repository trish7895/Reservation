package com.thiman.android.reservationmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Button;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button checkIn;
    Button checkOut;
    Button search;
    DatePickerDialog picker;
    TextView tvci,tvco,num1,num2,num3;
    Animation fadeIn;
    RelativeLayout rl1;
    LinearLayout ll2;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

    public void roomPick(){
        NumberPicker np1 = findViewById(R.id.np1);
     np1.setMinValue(0);
     np1.setMaxValue(20);
     np1.setWrapSelectorWheel(false);


    }

//    NumberPicker np1 = (NumberPicker) findViewById(R.id.np1);
//    NumberPicker np2 = (NumberPicker) findViewById(R.id.np2);
//    NumberPicker np3 = (NumberPicker) findViewById(R.id.np3);
//
//

//    public NumberPicker getNp3() {
//
//        np3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
//        return np3;
//    }


//    //Populate NumberPicker values from minimum and maximum value range
//    //Set the minimum value of NumberPicker
//        np1.setMinValue(0)
//    //Specify the maximum value/number of NumberPicker
//        np1.setMaxValue(10)
//
//    //Gets whether the selector wheel wraps when reaching the min/max value.
//        np1.setWrapSelectorWheel(true)
//
//    //Set a value change listener for NumberPicker
//        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//        @Override
//        public void onValueChange(NumberPicker picker, int oldVal, int newVal){
//            //Display the newly selected number from picker
//            tv.setText("Selected Number : " + newVal);
//        }
//    })


}
