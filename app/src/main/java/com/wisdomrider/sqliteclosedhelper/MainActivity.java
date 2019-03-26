package com.wisdomrider.sqliteclosedhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteClosedHelper closed = new SqliteClosedHelper(this, "DBNAME");






//        closed.updateTable(c);
//        ArrayList<Checks> checks = closed.whereOR(c);


//        Log.e(checks.toString(), checks.get(0).id + "-" + checks.get(0).age + "=" + checks.get(0).place + "-" + checks.get(0).name + "-" + checks.get(0).hey);
    }

}
