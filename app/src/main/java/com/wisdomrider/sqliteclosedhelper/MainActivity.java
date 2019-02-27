package com.wisdomrider.sqliteclosedhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wisdomrider.sqliteclosedhelper.newVersion.Method;
import com.wisdomrider.sqliteclosedhelper.newVersion.SqliteClosed;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteClosed closed = new SqliteClosed(this, "DBNAME");
        Checks c=new Checks();
        ArrayList<Method> m = closed.decompile(c);
        m.get(0).setValue(10);
        Log.e("Err",m.get(0).getUniqueKey());

    }

}
