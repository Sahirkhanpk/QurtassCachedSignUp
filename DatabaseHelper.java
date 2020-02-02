package com.example.myqurtassassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;




public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "signUp_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Node.CREATE_TABLE__SIGN_UP);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Node.TABLE_SIGN_UP);

        // Create tables again
        onCreate(db);
    }
   /* public int checkTable(SQLiteDatabase db){
         db.execSQL("SELECT * FROM sqlite_master WHERE name ='notes' and type='table'; ");
         return
    }*/

    public long insertNote(String node) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Node.COLUMN_BODY, node);



        // insert row
        long id = db.insert(Node.TABLE_SIGN_UP, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertAccount(String node) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Node.COLUMN_BODY, node);



        // insert row
        long id = db.insert(Node.TABLE_SIGN_UP, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }



    public int getAllcount() {
        String countQuery = "SELECT  * FROM " + Node.TABLE_SIGN_UP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }





    public List<String> getAllAccount() {
        List<String> Accounts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Node.TABLE_SIGN_UP + " ORDER BY " +
                Node.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String a =  cursor.getString(cursor.getColumnIndex(Node.COLUMN_BODY));


                Accounts.add(a);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return Accounts;
    }
}
