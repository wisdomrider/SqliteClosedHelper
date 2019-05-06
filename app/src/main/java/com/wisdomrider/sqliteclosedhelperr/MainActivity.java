package com.wisdomrider.sqliteclosedhelperr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wisdomrider.sqliteclosedhelper.SqliteClosedHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteClosedHelper helper = new SqliteClosedHelper(this, "DBNAME");
        helper.createTable(new Constant());
        helper.removeAll(new Constant());
        helper.insertTable(new Constant().setName("Age").setValue("123213dsadasdsa"));
        for (Constant c : helper.getAll(new Constant())) {
            Toast.makeText(this, c.value, Toast.LENGTH_SHORT).show();
        }
    }

}
