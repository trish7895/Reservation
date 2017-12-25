package com.thiman.android.reservationmanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.thiman.android.reservationmanager.Model.User;

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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please Waiting");
                mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Check if user not exists
                            if ((dataSnapshot.child(edtPhnNo.getText().toString()).exists()) ) {


                                // Get User Information
                                mDialog.dismiss();
                                User user = dataSnapshot.child(edtPhnNo.getText().toString()).getValue(User.class);


                                if (user.getPassword().equals(edtPwd.getText().toString())) {
                                    Toast.makeText(MainActivity.this, "Login Successfully...!!!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Login Failed!!! Enter Correct Details", Toast.LENGTH_SHORT).show();
                                }

                            }

                            else{
                                mDialog.dismiss();
                                Toast.makeText(MainActivity.this, "User Does Not Exists ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

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
