package com.ciacavus.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ciaran on 01/06/2016.
 */
public class DBAdapter {

    private static final String ID = "Id";

    private static final String NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "students";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private static final String DATABASE = "create table students (Id integer primary key autoincrement" +
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

    //method to open the database
    public DBAdapter open() throws SQLException {
        //get the db
        db = DBHelper.getWritableDatabase();
        //return instance of the database
        return this;
    }

    //method to insert contacts
    public long insertContacts(String name, String email) {

        ContentValues initial = new ContentValues();

        initial.put(NAME,name);
        initial.put(KEY_EMAIL,email);

        return db.insert(DATABASE_TABLE,null,initial);
    }

    //method to close the database
    public void close() {
        //close the db
        DBHelper.close();
    }

    //delete specific contact
    public boolean deleteContact(long rowID)
    {
        return db.delete(DATABASE_TABLE, ID + "-" + rowID,null) > 0;
    }

    //retrieve all contacts
    public Cursor getAllContacts()
    {
        //return a cursor query
        return db.query(DATABASE_TABLE, new String[] {ID, NAME, KEY_EMAIL},null,null,null,null,null);
    }

    //get specific contact
    public Cursor getContact(long rowId) throws SQLException{
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {ID,NAME,KEY_EMAIL}, ID + "=" + rowId,null,null,null,null,null);

        if(mCursor != null)
        {
            mCursor.moveToFirst();
        }

        return mCursor;
    }

    public int updateContact(long rowId, String name, String email)
    {
        //create the arguement variable
        ContentValues args = new ContentValues();

        //put arguments in the variable to be used in the query
        args.put(NAME,name);
        args.put(KEY_EMAIL,email);

        //return
        return db.update(DATABASE_TABLE, args, ID + "=" + rowId,null);

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
