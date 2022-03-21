package com.example.android.newsofindia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataManager extends SQLiteOpenHelper {
    private static final String dbName = "Login_Data_Info";

    public DataManager(@Nullable Context context) {
        super(context,dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String qryInfo = "CREATE TABLE signUpTable(_Id INTEGER PRIMARY KEY AUTOINCREMENT,_username TEXT NOT NULL,_password TEXT NOT NULL )";
        sqLiteDatabase.execSQL(qryInfo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS signUpTable");
    }

    public float addRecord(String userId, String password){
        SQLiteDatabase sqld = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("_username",userId);
        cv.put("_password",password);
       float result = sqld.insert("signUpTable",null,cv);
      return result;
    }
}
