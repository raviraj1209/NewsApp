package com.example.android.newsofindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password_changing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_changing);

        Button button = findViewById(R.id.setpass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cp = findViewById(R.id.currpass);
                EditText user = findViewById(R.id.useridtext);
                EditText np = findViewById(R.id.newpass);
                EditText cnp = findViewById(R.id.confnewpass);
                String cps = cp.getText().toString();
                String useridst = user.getText().toString();
                String nps = np.getText().toString();
                String cnps = cnp.getText().toString();

                StringBuffer buffer = new StringBuffer();
                StringBuffer buffer2 = new StringBuffer();
                int id = -1 ;

                DataManager dataManager = new DataManager(Password_changing.this);
                SQLiteDatabase database = dataManager.getReadableDatabase();
                Cursor cr = database.rawQuery("SELECT * FROM signUpTable",null);
                String username = "";
                if (cr.getCount()==0){
                    Toast.makeText(Password_changing.this, "No Username found ", Toast.LENGTH_SHORT).show();
                }else {
                    while (cr.moveToNext()) {
                        if (cr.getString(1).equals(useridst)) {
                            username = (cr.getString(1));
                            buffer2.append(cr.getString(2));
                            id = cr.getInt(0);

                            break;
                        }

                    }
                    if (useridst.equals(username) && cps.equals(buffer2.toString())){
                        if (nps.equals(cnps)){
                            SQLiteDatabase mDb= dataManager.getWritableDatabase();
                            ContentValues args = new ContentValues();
                            args.put("_password", nps);
                            mDb.update("signUpTable", args,  "_Id=" + id, null);
                            Toast.makeText(Password_changing.this, "Successfully Password Changed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Password_changing.this,Sign_In_page.class));

                        }else
                            Toast.makeText(Password_changing.this, "Mismatch Created Password", Toast.LENGTH_SHORT).show();
                    }else  Toast.makeText(Password_changing.this, "Incorrect Username or Password ", Toast.LENGTH_SHORT).show();

                }
                Password_changing.super.finish();
            }
        });
    }

}