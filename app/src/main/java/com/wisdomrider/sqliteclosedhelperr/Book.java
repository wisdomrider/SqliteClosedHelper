package com.wisdomrider.sqliteclosedhelperr;

import android.widget.ImageView;

import com.wisdomrider.sqliteclosedhelper.MethodAnnotations;
import com.wisdomrider.sqliteclosedhelper.SqliteAnnotations;

public class Book {
    @SqliteAnnotations.Primary
    @SqliteAnnotations.AutoIncrement
    int bookId; //here bookId is primary key and it increases by +1 everytime

    @SqliteAnnotations.Nullable(isNullable = false)
    float cost; //cost cannot be null


    @MethodAnnotations.Exclude
    ImageView image; //image is excluded from database as this is not a valid data type


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
