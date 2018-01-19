package com.thiman.android.reservationmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button checkIn;
    Button checkOut;
    DatePickerDialog picker;
    TextView tvci;
    TextView tvco;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
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


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        int id = item.getItemId();

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
                findViewById(R.id.id1).setVisibility(View.GONE);
                findViewById(R.id.id2).setVisibility(View.VISIBLE);
            } else

                if (id == R.id.nav_rooms) {

            } else if (id == R.id.nav_RoomDe) {

            } else if (id == R.id.nav_promo) {

            } else if (id == R.id.nav_reports) {

            } else if (id == R.id.nav_currency) {

            } else if (id == R.id.nav_settings) {

            } else if (id == R.id.nav_language) {

            } else if (id == R.id.nav_help) {

            } else if (id == R.id.nav_send) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "Confirming Booking");
                intent.putExtra(Intent.EXTRA_TEXT, "Your Reservation has been confirmed.Contact us for more Details.");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            } else if (id == R.id.nav_about) {

            } else if (id == R.id.nav_share) {

            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;



    }


        public void chkIn(View v) {


            final Calendar cldr1 = Calendar.getInstance();
            int dayci = cldr1.get(Calendar.DAY_OF_MONTH);
            int month = cldr1.get(Calendar.MONTH);
            int year = cldr1.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(Home.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            tvci.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            tvco.setText(dayOfMonth+1 +"-"+ (monthOfYear + 1) + "-" + year);
                        }
                    }, year, month, dayci);

            //Get the DatePicker instance from DatePickerDialog
            DatePicker dp = picker.getDatePicker();
            //Set the DatePicker minimum date selection to current date
            dp.setMinDate(cldr1.getTimeInMillis());//get the current day
            //dp.setMinDate(System.currentTimeMillis() - 1000);// Alternate way to get the current day

            //Add 6 days with current date
            cldr1.add(Calendar.DAY_OF_MONTH,6);

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
                                    // Do something when click positive button
                                    tvco.setText(dayOfMonth+1 + "-" + (monthOfYear + 1) + "-" + year);

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
        cldr.add(Calendar.DAY_OF_MONTH,6);

        //Set the maximum date to select from DatePickerDialog
        dp.setMaxDate(cldr.getTimeInMillis());
        //Now DatePickerDialog have 7 days range to get selection any one from those dates

        picker.show();

    }

//    NumberPicker np1 = (NumberPicker) findViewById(R.id.np1);
//    NumberPicker np2 = (NumberPicker) findViewById(R.id.np2);
//    NumberPicker np3 = (NumberPicker) findViewById(R.id.np3);
//
//
//
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
//

}
