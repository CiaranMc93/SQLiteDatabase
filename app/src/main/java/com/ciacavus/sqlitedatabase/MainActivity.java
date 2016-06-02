package com.ciacavus.sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a new instance of the database
        DBAdapter db = new DBAdapter(this);

//        //open the database
//        db.open();
//        //insert our data
//        long id = db.insertContacts("Ciaran","ciaranmcmanus@live.ie");
//        id = db.insertContacts("Avril","avrildooley@live.ie");
//        //db close
//        db.close();

        db.open();

        Cursor c = db.getAllContacts();

        //perform a query and in a do while loop,
        //run over each search result of the query
        if(c.moveToFirst())
        {
            do{
                DisplayContacts(c);
            }
            while(c.moveToNext());


        }

    }

    private void DisplayContacts(Cursor c) {
        Toast.makeText(this,"id: " + c.getString(0) + "\n" + "Name: " + c.getString(1) + "Email: " + c.getString(2),Toast.LENGTH_LONG).show();
    }
}
