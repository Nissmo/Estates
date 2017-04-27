package com.example.nissmo.estates.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nissmo.estates.Structure.Estate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nissmo on 21.04.17.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Estates";

    private static final String TABLE_ESTATES = "estates";
    private static final String KEY_ID = "id";
    private static final String KEY_REGION = "region";
    private static final String KEY_TYPE = "type";
    private static final String KEY_PRICE = "price";
    private static final String KEY_FLOORS = "floors";
    private static final String KEY_AREA = "area";
    private static final String KEY_ROOMS = "rooms";
    private static final String KEY_TEXT = "text";


    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ESTATES_TABLE = "CREATE TABLE " + TABLE_ESTATES + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_REGION + " TEXT," + KEY_TYPE + " TEXT," + KEY_PRICE + " INTEGER," + KEY_AREA + " INTEGER," + KEY_FLOORS + " INTEGER," +
                KEY_ROOMS + " INTEGER," + KEY_TEXT + " TEXT" + ")";
        db.execSQL(CREATE_ESTATES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTATES);
        onCreate(db);
    }

    public void addEstate(Estate estate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REGION,estate.getRegion());
        values.put(KEY_PRICE,estate.getPrice());
        values.put(KEY_TYPE,estate.getType());
        values.put(KEY_FLOORS,estate.getFloors());
        values.put(KEY_AREA,estate.getArea());
        values.put(KEY_ROOMS,estate.getArea());
        values.put(KEY_TEXT,estate.getText());

        db.insert(TABLE_ESTATES,null,values);
        db.close();
    }
    public List<Estate> getAllEstates()
    {
        List<Estate> estateList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_ESTATES;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        {
            do {
                Estate estate = new Estate();
                estate.setRegion(cursor.getString(1));
                estate.setType(cursor.getString(2));
                estate.setPrice(cursor.getString(3));
                estate.setArea(cursor.getString(4));
                estate.setFloors(cursor.getString(5));
                estate.setRooms(cursor.getString(6));
                estate.setText(cursor.getString(7));
                estateList.add(estate);
            }while (cursor.moveToNext());
        }
        return estateList;
    }
    public List<Estate> getTypeEstates(String selecting, String[] args)
    {
        List<Estate> estateList = new ArrayList<>();
        String[] columnsToReturn = { "*"};
        String selectQuery = "SELECT * FROM " + TABLE_ESTATES + " WHERE " + selecting;
        String selection = selecting;
        String[] selectionArgs = args;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_ESTATES,columnsToReturn,selection,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do {
                Estate estate = new Estate();
                estate.setRegion(cursor.getString(1));
                estate.setType(cursor.getString(2));
                estate.setPrice(cursor.getString(3));
                estate.setArea(cursor.getString(4));
                estate.setFloors(cursor.getString(5));
                estate.setRooms(cursor.getString(6));
                estate.setText(cursor.getString(7));
                estateList.add(estate);
            }while (cursor.moveToNext());
        }
        return estateList;
    }
}
