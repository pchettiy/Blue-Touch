package com.example.praba1110.touchthis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by praba1110 on 17/7/15.
 */
public class SQLhandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    Cursor c;
    public SQLhandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Scores_", factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE scores("+ "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,"+"points INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS scores");
        onCreate(db);
        db.close();
    }
    public void add(String name, int point){
        ContentValues values=new ContentValues();
        values.put("name", name);
        values.put("points", point);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("scores", null, values);
    }
    public List<String> getnames() {
        String temp = "";
        List<String> names = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT name FROM scores WHERE 1 ORDER BY points DESC;";
        c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            temp = c.getString(c.getColumnIndex("name"));
            if (temp != null) {
                names.add(temp);

            }
            c.moveToNext();

        }

        return names;
    }
    public List<Integer> getpoints() {
        int temp=-1 ;
        List<Integer> points = new ArrayList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT points FROM scores WHERE 1 ORDER BY points DESC;";
        c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            temp = c.getInt(c.getColumnIndex("points"));
            if (temp != -1) {
                points.add(temp);

            }
            c.moveToNext();

        }

        return points;
    }
    public List<Integer> getsno() {
        int temp=-1 ;
        List<Integer> points = new ArrayList<Integer>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT _id FROM scores WHERE 1;";
        c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            temp = c.getInt(c.getColumnIndex("_id"));
            if (temp != -1) {
                points.add(temp);

            }
            c.moveToNext();

        }

        return points;
    }
}
