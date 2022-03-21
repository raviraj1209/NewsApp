package com.example.android.newsofindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_In_page extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);



        TextView forgetPasswordTextView = findViewById(R.id.forgetPassword_id);
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Sign_In_page.this,forget_password_page.class);
                startActivity(intent);
                Sign_In_page.super.finish();
            }
        });

        Button loginButton = findViewById(R.id.Login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText username1 = findViewById(R.id.username_signIn);
                String username2 = username1.getText().toString();
                if (username2.equals("")){
                    Toast.makeText(Sign_In_page.this, "Please Enter your Username", Toast.LENGTH_SHORT).show();
                }else{
                EditText password1 = findViewById(R.id.passwordSignIn);
                String password = password1.getText().toString();
                StringBuffer buffer = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();
                StringBuffer buffer3 = new StringBuffer();

                DataManager dataManager = new DataManager(Sign_In_page.this);
                SQLiteDatabase database = dataManager.getReadableDatabase();
                Cursor cr = database.rawQuery("SELECT * FROM signUpTable",null);

              if (cr.getCount()==0){
                  Toast.makeText(Sign_In_page.this, "Incorrect Info ", Toast.LENGTH_SHORT).show();
              }else {
                  while (cr.moveToNext()) {
                      if (cr.getString(1).equals(username2)) {
                          buffer.append(cr.getString(1));
                          buffer2.append(cr.getString(2));
                          buffer3.append(cr.getString(0));
                          break;
                      }
                  }
                      if (username2.equals(buffer.toString()) && password.equals(buffer2.toString()) ){
                           Intent i = new Intent(Sign_In_page.this,News_page.class);
                           i.putExtra("toShow",username2);
                           i.putExtra("id_ref",buffer3.toString());
                           startActivity(i);
                          Sign_In_page.super.finish();

                      }else Toast.makeText(Sign_In_page.this, "Incorrect Info "  , Toast.LENGTH_SHORT).show();

              }


            }}
        });


        }
    }
