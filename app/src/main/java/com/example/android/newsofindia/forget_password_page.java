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

public class forget_password_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_page);

        Button button = findViewById(R.id.button4getpassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usertxt = findViewById(R.id.getusername);
                String usernm = usertxt.getText().toString();
                TextView passtxt = findViewById(R.id.textView4password);
                StringBuffer buffer = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();

                DataManager dataManager = new DataManager(forget_password_page.this);
                SQLiteDatabase database = dataManager.getReadableDatabase();
                Cursor cr = database.rawQuery("SELECT * FROM signUpTable",null);
                if (cr.getCount()==0){
                    Toast.makeText(forget_password_page.this, "No Username found ", Toast.LENGTH_SHORT).show();
                }else {
                    while (cr.moveToNext()) {
                        if (cr.getString(1).equals(usernm)) {
                            buffer.append(cr.getString(1));
                            buffer2.append(cr.getString(2));
                            break;
                        }
                    }
                    if (usernm.equals(buffer.toString())){
                        passtxt.setText("Your Password "+" is "+buffer2.toString());
                    }else  Toast.makeText(forget_password_page.this, "No Username found ", Toast.LENGTH_SHORT).show();

            }
        }
        });
    }

    }
