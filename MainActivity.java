package com.thiman.android.reservationmanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {
    LinearLayout lolgn;
    Animation fadeIn;
    Button btnLogin;
    EditText edtPhnNo, edtPwd;
    TextView signIn;

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

        lolgn = findViewById(R.id.lolgn);
       

        fadeIn = AnimationUtils.loadAnimation(this,R.anim.imageanim);
        lolgn.setAnimation(fadeIn);

        btnLogin = findViewById(R.id.btn_login);
        signIn = findViewById(R.id.link_to_sign_up);

        edtPhnNo =(MaterialEditText)findViewById(R.id.edt_phn);
        edtPwd = (MaterialEditText)findViewById(R.id.edt_pwd);

        // Init Firebase
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_user = database.getReference("user");




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent homeIntent = new Intent(MainActivity.this,Home.class);
                startActivity(homeIntent);
                finish();
            }

        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.lk/"));
                startActivity(signIn);
            }
        });

    }
}
