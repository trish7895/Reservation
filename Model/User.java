package com.thiman.android.reservationmanager.Model;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Asus on 12/25/2017.
 */

public class User extends AppCompatActivity {
 // database attributesas there are
      private String Email;
      private String Password;

      public User(){

      }

      public  User(String email, String password){

          Email = email;
          Password = password;
      }

      public String getEmail() {
            return Email;
      }

      public void setEmail(String email) {
            Email = email;
      }

      public String getPassword() {
            return Password;
      }

      public void setPassword(String password) {
            Password = password;
      }
}
