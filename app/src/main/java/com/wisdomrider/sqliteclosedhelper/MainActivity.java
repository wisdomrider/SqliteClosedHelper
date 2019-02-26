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


        Checks checks = new Checks();
        helper.createTableFromClass(checks);
        checks.setAge(70);
        checks.setFirst("heya1");
        checks.setHell(1.34234134154f);
        checks.setLastname("ASda71");
        checks.setOops(1);

//        helper.insertTableFromClass(checks);


        ArrayList<Checks> checks1 = helper.getAll(new Checks());
        ArrayList<Checks> checks2 = helper.getWhere(checks);
//        Log.e("ERQ!", checks1.get(0).getFirst());
//        Log.e("ERQ!", checks2.get(0).getFirst());

       helper.updateTableFromClass(checks,checks.getFirst());



    }
}
