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

public class Sign_Up_page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        TextView alreadyuser = findViewById(R.id.Already_account_id);
        alreadyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_Up_page.this,Sign_In_page.class);
                startActivity(intent);
                Sign_Up_page.super.finish();
            }
        });

        Button regButton = findViewById(R.id.registerButton);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText useridSignup = findViewById(R.id.editTextTextPersonName);
                String userreg = useridSignup.getText().toString();
                EditText enterPassword = findViewById(R.id.editTextTextPersonName2);
                String enterpassreg = enterPassword.getText().toString();
                EditText confirmPassword = findViewById(R.id.editTextTextPersonName3);
                String confmPassreg = confirmPassword.getText().toString();

                DataManager dataManagerObj = new DataManager(Sign_Up_page.this);
                SQLiteDatabase db = dataManagerObj.getReadableDatabase();
                Cursor findingUserId = db.rawQuery("SELECT * FROM signUpTable",null);
                StringBuffer check = new StringBuffer();
                String check1 = "";
                final boolean  b = enterpassreg.equals(confmPassreg) && enterpassreg.length() != 0;
                if (findingUserId.getCount() != 0) {
                    while (findingUserId.moveToNext()) {
                        if (findingUserId.getString(1).equals(userreg)) {
                            check.append(findingUserId.getString(1));
                            break;
                        }
                    }

                if (userreg.equals(check1)){
                    Toast.makeText(Sign_Up_page.this, "please Enter Username", Toast.LENGTH_SHORT).show();
                } else{
                    if (!userreg.equals(check.toString())) {
                        if (b) {
                            float res = dataManagerObj.addRecord(userreg, enterpassreg);
                            if (res == -1) {
                                Toast.makeText(Sign_Up_page.this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Sign_Up_page.this,Sign_In_page.class));
                                Sign_Up_page.super.finish();
                                Toast.makeText(Sign_Up_page.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            }
                        } else
                        { Toast.makeText(Sign_Up_page.this, "Password Mismatch", Toast.LENGTH_SHORT).show();}
                    } else {Toast.makeText(Sign_Up_page.this, "Username Not Available", Toast.LENGTH_SHORT).show();}

                }
            }
                else { if (userreg.equals(check1)){
                    Toast.makeText(Sign_Up_page.this, "please Enter Username", Toast.LENGTH_SHORT).show();
                } else{
                        if (b) {
                            float res = dataManagerObj.addRecord(userreg, enterpassreg);
                            if (res == -1) {
                                Toast.makeText(Sign_Up_page.this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Sign_Up_page.this, Sign_In_page.class));
                                Toast.makeText(Sign_Up_page.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                Sign_Up_page.super.finish();

                            }
                        } else { Toast.makeText(Sign_Up_page.this, "Password Mismatch", Toast.LENGTH_SHORT).show();}


            }}

            }
        });

    }}
