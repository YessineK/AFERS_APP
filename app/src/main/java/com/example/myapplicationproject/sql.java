package com.example.myapplicationproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class sql extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_IMAGE = "image_data";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_IMAGE + " BLOB" + ")";

    public sql(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do nothing for now
    }

    public long addData(String title, String address, Bitmap image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageData = stream.toByteArray();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_IMAGE, imageData);

        long newRowId = db.insert(TABLE_NAME, null, values);

        db.close();
        return newRowId;
    }
    public List<Data> getAllData() {
        List<Data> dataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_ADDRESS,
                COLUMN_IMAGE
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS));
            byte[] imageData = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
            Bitmap image = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

            Data data = new Data(id, title, address, image);
            dataList.add(data);
        }

        cursor.close();
        db.close();

        return dataList;
    }
}
