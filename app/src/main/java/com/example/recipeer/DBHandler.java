package com.example.recipeer;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;

import java.text.BreakIterator;

import static android.os.Build.ID;

public class DBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recipesDB.db";
    public static final String TABLE_NAME = "DataModel";
    public static final String COLUMN_ID = "Recipes";
    public static final String COLUMN_NAME = "Ingredients";

    //initialize the database
    public DBHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                "INTEGER PRIMARYKEY," + COLUMN_NAME + "TEXT )";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result = "";
        String query = "Select*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(DataModel datamodel) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, datamodel.getRecipes());
        values.put(COLUMN_NAME, datamodel.getIngredients());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public DataModel findHandler(String in) {
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_NAME + " = " + "'" + in + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        DataModel datamodel = new DataModel();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            datamodel.setRecipes(cursor.getString(0));
            datamodel.setIngredients(cursor.getString(1));
            cursor.close();
        } else {
            datamodel = null;
        }
        db.close();
        return datamodel;
    }
    public boolean deleteHandler(String re) {
        boolean result = false;
        String query = "Select*FROM " + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(re) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        DataModel datamodel = new DataModel();
        if (cursor.moveToFirst()) {
            datamodel.setRecipes(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                String.valueOf(datamodel.getRecipes())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(String re, String in) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, re);
        args.put(COLUMN_NAME, in);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}
