package com.example.damian.nbtrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static  final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement,"
            +"account text UNIQUE,"
            +"password text)";
    public static  final String CREATE_GOODS = "create table Goods ("
            + "id integer primary key autoincrement,"
            +"name text,"
            +"imageId int,"
            +"category int,"
            +"price real,"
            +"describe text)";
    private Context mContext;

    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_GOODS);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Goods");
        onCreate(db);
    }
}
