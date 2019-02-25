package com.wisdomrider.sqliteclosedhelper;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteClosedHelper helper = new SqliteClosedHelper(this, "sad");


        Checks checks = new Checks();
        checks.setAge(10);
        checks.setFirst("Aviskhek");
        checks.setLastname("asdjkgasd");
        helper.createTableFromClass(checks);

    }
}
