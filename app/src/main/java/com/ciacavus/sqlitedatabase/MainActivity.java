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

        db.open();

        long id = db.insertContacts("Bob","bob.ross@live.ie");
        id = db.insertContacts("Forrest","forrest.run@live.ie");

        //create an SQL cursor for the functionality of getting all the contacts
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

    //display the contacts in a toast from the cursor values
    private void DisplayContacts(Cursor c) {
        Toast.makeText(this,"id: " + c.getString(0) + "\n" + "Name: " + c.getString(1) + "Email: " + c.getString(2),Toast.LENGTH_LONG).show();
    }
}
