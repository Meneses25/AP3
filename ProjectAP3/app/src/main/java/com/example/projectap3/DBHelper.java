package com.example.projectap3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists users(txtUser2 TEXT primary key, txtPass TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");

    }

    public boolean insertData(String txtUser, String txtPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("txtUser", txtUser);
        contentValues.put("txtPass", txtPass);
        long result = db.insert("users", null, contentValues);

        if (result==-1) return false;
        else
            return true;

    }

    public Boolean checktxtUser(String txtUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where txtUser = ?", new String[] {txtUser});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checktxtUsertxtPass(String txtUser, String txtPass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where txtUser = ? and txtPass = ?", new String[] {txtUser, txtPass});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
