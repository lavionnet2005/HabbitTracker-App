package com.example.android.habbittracker.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.habbittracker.data.HabbitTrackerContract.HabbitTrackerEntry;

/**
 * Created by lkatta on 2/9/17.
 */

public class HabbitTrackerDbHelper extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "ht.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_STATEMENT = "CREATE TABLE " + HabbitTrackerEntry.TABLE_NAME +
            " (" + HabbitTrackerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HabbitTrackerEntry.COLUMN_ACTIVITY_NAME + " TEXT NOT NULL, "
            + HabbitTrackerEntry.COLOUMN_ACTIVITY_TIME + " INTEGER NOT NULL DEFAULT 0);" ;

    private static final String SQL_DELETE_STATEMENT = "DROP TABLE IF EXISTS " + HabbitTrackerEntry.TABLE_NAME ;


    public HabbitTrackerDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STATEMENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void deleteEntries(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_STATEMENT);
        onCreate(db);

    }
}
