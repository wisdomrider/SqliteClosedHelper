package com.wisdomrider.sqliteclosedhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteClosedHelper helper = new SqliteClosedHelper(this,"sad");
        helper.setTable("avishesak").setTableFields("a",Wisdom.TEXT()).setTableFields("aw",Wisdom.TEXT()).setTableFields("ao",Wisdom.TEXT()).create();

    }
}
