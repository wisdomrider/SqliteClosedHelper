package com.wisdomrider.sqliteclosedhelper;

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
        SqliteClosedHelper helper = new SqliteClosedHelper(this,"sad");
        helper.setTable("avishesak")
                .setTableFields("a",Wisdom.TEXT(),Wisdom.NOTNULL())
                .setTableFields("aw",Wisdom.TEXT(),Wisdom.NOTNULL())
                .setTableFields("ao",Wisdom.TEXT(),Wisdom.NOTNULL())
                .create()
                .insertFields("a","aheck")
                .insertFields("aw","tike")
                .insertFields("ao","crystal")
                .insert();
        helper.updateFields("a","basd")
                .update("aasddas","aheck");
        Cursor a=helper.setTable("avishesak")
                .get("where a='aheck'");
        while (a.moveToNext()){
            Toast.makeText(this, a.getString(1), Toast.LENGTH_SHORT).show();
        }


    }
}
