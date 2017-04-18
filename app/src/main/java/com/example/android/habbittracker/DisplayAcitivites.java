package com.example.android.habbittracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.habbittracker.data.HabbitTrackerContract.HabbitTrackerEntry;
import com.example.android.habbittracker.data.HabbitTrackerDbHelper;

import java.util.ArrayList;

/**
 * Created by lkatta on 2/9/17.
 */

public class DisplayAcitivites extends AppCompatActivity {

    HabbitTrackerDbHelper helperDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        helperDb = new HabbitTrackerDbHelper(this);

        ArrayList<String> values = getValues();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

        ListView listView = (ListView) findViewById(R.id.list_display_view);
        listView.setAdapter(adapter);


    }

    @NonNull
    private ArrayList<String> getValues() {

        ArrayList<String> values = new ArrayList();

        Cursor cursor = readValues();
        if (cursor.getCount() == 0) {
            values.add("NO ACTIVITES TO DISPLAY");
        } else {
            try {
                int nameColumnIndex = cursor.getColumnIndex(HabbitTrackerEntry.COLUMN_ACTIVITY_NAME);
                int timeColumnIndex = cursor.getColumnIndex(HabbitTrackerEntry.COLOUMN_ACTIVITY_TIME);

                while (cursor.moveToNext()) {

                    String name = cursor.getString(nameColumnIndex);
                    int time = cursor.getInt(timeColumnIndex);

                    String list = "\n" + name + " - " + time;
                    values.add(list);
                }

            } finally {
                cursor.close();
            }
        }
        return values;
    }

    private Cursor readValues() {
        SQLiteDatabase db = helperDb.getReadableDatabase();
        String[] projection = {
                HabbitTrackerEntry._ID,
                HabbitTrackerEntry.COLUMN_ACTIVITY_NAME,
                HabbitTrackerEntry.COLOUMN_ACTIVITY_TIME};


        Cursor cursor = db.query(HabbitTrackerEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }
}
