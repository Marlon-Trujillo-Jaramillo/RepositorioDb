package com.example.inventariodb;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class dbProduct extends SQLiteOpenHelper{
    String tbProduct = "Create Table product (idproduct text,productname text, precio integer, existencia integer) ";
    String tbLoan = "Create Table loan(idloan integer primary key autoincrement, idproduct text, iduser text, dateloan text)";
    public dbProduct(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbProduct);
        db.execSQL(tbLoan);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table Product");
        db.execSQL(tbProduct);
        db.execSQL("Drop Table loan");
        db.execSQL(tbLoan);

    }
}
