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
        SqliteClosedHelper closed = new SqliteClosedHelper(this, "DBNAME");
        Checks c = new Checks();
        c.id = 50;
        c.hey = true;
        c.age = 20f;
        c.place = "fucka";
//        closed.createTable(c);
//        closed.updateTable(c);
        ArrayList<Checks> checks = closed.whereOR(c);

        Log.e(checks.toString(), checks.get(0).id + "-" + checks.get(0).age + "=" + checks.get(0).place + "-" + checks.get(0).name + "-" + checks.get(0).hey);
    }

}
