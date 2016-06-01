package com.ciacavus.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create a new instance of the database
        DBAdapter db = new DBAdapter(this);

        //open the database
        db.open();


    }
}
