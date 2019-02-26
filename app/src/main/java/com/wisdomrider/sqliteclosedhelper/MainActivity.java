package com.wisdomrider.sqliteclosedhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteClosedHelper helper = new SqliteClosedHelper(this, "sad");
         Checks checks=new Checks();
         checks.setBiodataphoto("ASdasdas");
         checks.setPhone("ASdasdassdasd111111");
         checks.setEmail("avishekzone@@gmailc.0om");
         helper.createTableFromClass(checks);
         helper.updateTableFromClass(checks,checks.getEmail());



    }
}
