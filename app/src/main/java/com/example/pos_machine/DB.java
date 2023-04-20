package com.example.pos_machine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    public static final String CODE="code";
    public static final String ITEM="item";
    public static final String UNIT="unit";
    public static final String UNIT_PRICE="unit_price";
    public static final String sale_code="sale_code";
    public DB( Context context) {
        super(context, "PosDB.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table ItemInformation(CODE TEXT primary key, ITEM TEXT , UNIT INT , UNIT_PRICE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop Table if exists ItemInformation");
    }

    public Boolean insertInfo(String code, String item, int unit, String unit_price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CODE , code);
        contentValues.put(ITEM , item);
        contentValues.put(UNIT , unit);
        contentValues.put(UNIT_PRICE , unit_price);
        long result = db.insert("ItemInformation", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public String searchItem(String searchTerm) {
        String itemName = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"item"};

        // Define the WHERE clause for the query
        String selection = "code = ?";

        // Define the values for the WHERE clause
        String[] selectionArgs = {searchTerm};

        // Query the database
        Cursor cursor = db.query("ItemInformation", projection, selection, selectionArgs, null, null, null);

        // Get the item name from the cursor
        if (cursor.moveToNext()) {
            itemName = cursor.getString(cursor.getColumnIndexOrThrow("ITEM"));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        // Return the item name
        return itemName;
    }


    public String addItem1(String searchTerm) {
        String itemName = null;
        String que = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"unit"};

        // Define the WHERE clause for the query
        String selection = "code = ?";

        // Define the values for the WHERE clause
        String[] selectionArgs = {searchTerm};

        // Query the database
        Cursor cursor = db.query("ItemInformation", projection, selection, selectionArgs, null, null, null);

        // Get the item name from the cursor
        if (cursor.moveToNext()) {
            que = cursor.getString(cursor.getColumnIndexOrThrow("UNIT"));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        // Return the item name
        return que;
    }


    public String addItem2(String searchTerm) {
        String itemName = null;
        String price = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {"unit_price"};

        // Define the WHERE clause for the query
        String selection = "code = ?";

        // Define the values for the WHERE clause
        String[] selectionArgs = {searchTerm};

        // Query the database
        Cursor cursor = db.query("ItemInformation", projection, selection, selectionArgs, null, null, null);

        // Get the item name from the cursor
        if (cursor.moveToNext()) {
            price = cursor.getString(cursor.getColumnIndexOrThrow("UNIT_PRICE"));
        }

        // Close the cursor and database
        cursor.close();
        db.close();

        // Return the item name
        return price;
    }


}
