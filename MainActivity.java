package com.thiman.android.reservationmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

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
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout lolgn;
    Animation fadeIn;
    Button btnLogin;
    EditText edtPhnNo, edtPwd;
    TextView signIn;
    public static final String MyPREFERENCES = "MyPrefs" ;


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

                String username=edtPhnNo.getText().toString();
                String password=edtPwd.getText().toString();
                if(!username.isEmpty()||!username.equalsIgnoreCase("")||!password.isEmpty()||!password.equalsIgnoreCase("")){
                    MyAsync login = new MyAsync();
                    login.execute(username,password);
                }

                else if(username.isEmpty()||password.isEmpty()){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_HOLO_DARK);

                    builder1.setMessage("Try Again?");

                    builder1.setCancelable(false);
                    builder1.setTitle("Enter userName & Password ");
                    builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });

                    AlertDialog dialog = builder1.create();
                    dialog.show();

                }

                else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_HOLO_DARK);

                    builder1.setMessage("Try Again?");

                    builder1.setCancelable(false);
                    builder1.setTitle("Enter Correct Credentials ");
                    builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {



                        }
                    });

                    AlertDialog dialog = builder1.create();
                    dialog.show();
                }

            }

        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(Intent.ACTION_VIEW, Uri.parse("http://quadro.projects.mrt.ac.lk:8000/"));
                startActivity(signIn);
            }
        });

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
        int progress=0;

        try{

            URL loginURL = new URL("http://faceit.projects.mrt.ac.lk:3002/login");
//            URL loginURL = new URL("http://10.0.2.2:3002/login");
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
                System.out.println("this is my response code : "+statusCode);
                JSONObject error_response =new JSONObject();
                try {
                    error_response.put("status",String.valueOf(statusCode));
                    error_response.put("message","Login failed !");
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
        System.out.println("this is my response : "+s);
        try {
            JSONObject response = new JSONObject(s);

            int statuscode = response.getInt("status");
            if(statuscode==200){
                SharedPreferences reservationSettings = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor reservationSettingsEditor = reservationSettings.edit();
                reservationSettingsEditor.putString("access_token", ((JSONObject)response.get("data")).getString("token"));
                reservationSettingsEditor.apply();


                Intent homeIntent = new Intent(MainActivity.this,Home.class);
                startActivity(homeIntent);
                finish();
            }else if(statuscode == 500){

                Log.d("status",response.getString("status"));
                Toast.makeText(getApplicationContext(),response.get("message").toString(), Toast.LENGTH_LONG).show();
            }
            else {
//                Log.d("status",response.getString("status"));
//                Toast.makeText(getApplicationContext(),response.get("message").toString(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this,AlertDialog.THEME_HOLO_DARK);

                builder1.setMessage("Login Failed.Try Again?");

                builder1.setCancelable(false);
                builder1.setTitle("Enter Correct Credentials ");
                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                    }
                });

                AlertDialog dialog = builder1.create();
                dialog.show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


       // super.onPostExecute(s);
    }
}




}
