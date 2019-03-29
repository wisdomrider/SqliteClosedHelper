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
        SqliteClosedHelper helper = new SqliteClosedHelper(this, "DBNAME");
        helper.createTable(new Book());
        helper.removeAll(new Book());
        ArrayList<Book> books=new ArrayList<>();
        Book book1=new Book();
        Book book2=new Book();
        Book book3=new Book();
        book1.setBookId(01);
        book2.setBookId(02);
        book3.setBookId(03);
        book1.setCost(12f);
        book2.setCost(13f);
        book3.setCost(14f);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        helper.updateAll(books);
       ArrayList<Book> books1=helper.getAll(new Book());
      Log.e(
              "asd","ASD"
      );

    }

}
