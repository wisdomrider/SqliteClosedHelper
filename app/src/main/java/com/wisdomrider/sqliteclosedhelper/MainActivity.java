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
        if(!helper.ifTableExist("avi1")){
            Toast.makeText(this, "xaena", Toast.LENGTH_SHORT).show();
        }

        helper.setTable("avi1")
                .setTableFields("id",Wisdom.INTEGER(),Wisdom.PRIMARY_AUTOINCREMENT())
                .setTableFields("aa",Wisdom.INTEGER(),Wisdom.NOTNULL())
                .setTableFields("aw",Wisdom.TEXT(),Wisdom.NOTNULL())
                .setTableFields("ao",Wisdom.TEXT(),Wisdom.NOTNULL())
                .create()
                .insertFields("aa",1 )
                .insertFields("aw","tike")
                .insertFields("ao","crystal")
                .insert();



    }
}
