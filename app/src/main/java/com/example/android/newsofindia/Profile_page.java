package com.example.android.newsofindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Profile_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        TextView logoutText = findViewById(R.id.logout);
        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile_page.super.finish();
            }
        });


        TextView deleteText = findViewById(R.id.delete_acc);
        deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(getIntent().getStringExtra("id"));
                DataManager dataManager = new DataManager(Profile_page.this);
                SQLiteDatabase liteDatabase = dataManager.getReadableDatabase();
                liteDatabase.delete("signUpTable","_Id ="+a,null);
                Toast.makeText(Profile_page.this, "Successfully Account Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Profile_page.this,MainActivity.class));
                Profile_page.super.finish();

            }
        });

        TextView changePass = findViewById(R.id.change_pass);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile_page.this,Password_changing.class));
//                Profile_page.super.finish();
            }
        });

    }
}