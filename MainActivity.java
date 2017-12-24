package com.thiman.android.reservationmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout lolgn;
    Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        manager = (ImageView)findViewById(R.id.second);
//        fromBottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
//        manager.setAnimation(fromBottom);
//
//        reservation = (ImageView)findViewById(R.id.first);
//        fromTop = AnimationUtils.loadAnimation(this,R.anim.from_top);
//        reservation.setAnimation(fromTop);

        lolgn = (LinearLayout) findViewById(R.id.lolgn);
        fadeIn = AnimationUtils.loadAnimation(this,R.anim.imageanim);
        lolgn.setAnimation(fadeIn);
    }
}
