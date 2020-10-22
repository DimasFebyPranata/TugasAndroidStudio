package com.example.hd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "hd.db";
    public static final int DB_VERSION =1;

    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USER = "username";
    public static final String COLUMN_PASSWORD = "password";

    /**
     * create table users(
            id integer primary key autoincrement,
            email text,
            password text);
     */

    public static final  String CREATE_TABLE_USERS = "CREATE TABLE "+USER_TABLE+"("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER + " TEXT,"
            + COLUMN_PASSWORD + " TEXT);";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }

    public void addUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, username);
        values.put(COLUMN_PASSWORD, password);

        long id = db.insert(USER_TABLE,null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);

    }
    public boolean getUser (String username, String password) {
        String selectQuery = " select * from "+ USER_TABLE + " where "+
                COLUMN_USER + " = " + "'" +username+ "'" + " and " + COLUMN_PASSWORD + " = " + "'" +password+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0){

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}
