package com.ciacavus.sqlitedatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by ciaran on 01/06/2016.
 */
public class DBAdapter {

    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private static final String DATABASE = "create table students (id integer primary key autoincrement" +
            " name text not null, email text not null);";

    //database variables
    private Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(MainActivity mainActivity) {

    }

    public DBAdapter(Context ctx) {
        this.context = ctx;

        DBHelper = new DatabaseHelper(context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context, DATABASE, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try{
                //execute SQL query
                db.execSQL(DATABASE);
            }catch (SQLException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version" + oldVersion
            + " to " + newVersion + " will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS students");

            onCreate(db);
        }

    }

}
