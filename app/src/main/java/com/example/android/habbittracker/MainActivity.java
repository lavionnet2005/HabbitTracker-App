package com.example.android.habbittracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habbittracker.data.HabbitTrackerContract.HabbitTrackerEntry;
import com.example.android.habbittracker.data.HabbitTrackerDbHelper;

import static com.example.android.habbittracker.data.HabbitTrackerContract.HabbitTrackerEntry.COLOUMN_ACTIVITY_TIME;

/**
 * UI Displays fields to enter an ativity and time spend for it,
 * User can save the activites, get a list of all activites or clear the activities and start fresh.
 */
public class MainActivity extends AppCompatActivity {

    HabbitTrackerDbHelper helperDb;
    private EditText activity;
    private EditText time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = (EditText) findViewById(R.id.editTextHabbit);
        time = (EditText) findViewById(R.id.editTextTime);

        final Button saveButton = (Button) findViewById(R.id.savebutton);
        Button showButton = (Button) findViewById(R.id.showbutton);
        Button deleteButton = (Button) findViewById(R.id.dropButton);

        helperDb = new HabbitTrackerDbHelper(this);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertActivity();

            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent displayActivites = new Intent(MainActivity.this, DisplayAcitivites.class);
                startActivity(displayActivites);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteActivities();
            }
        });


    }

    private void insertActivity() {

        SQLiteDatabase db = helperDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        String timeNumber = time.getText().toString();
        String activityName = activity.getText().toString();

        if (timeNumber.isEmpty() || activityName.isEmpty()) {
            Toast.makeText(this, "Please enter activity name and minutes.", Toast.LENGTH_SHORT).show();
        } else {

            values.put(HabbitTrackerEntry.COLUMN_ACTIVITY_NAME, activityName);
            values.put(COLOUMN_ACTIVITY_TIME, Integer.parseInt(timeNumber));


            long newRowId = db.insert(HabbitTrackerEntry.TABLE_NAME, null, values);

            if (newRowId == -1) {
                Toast.makeText(this, "Activity not inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Activity inserted", Toast.LENGTH_SHORT).show();
            }

            activity.setText("");
            time.setText("");
        }

    }

    private void deleteActivities() {

        SQLiteDatabase db = helperDb.getWritableDatabase();

        Cursor cursor = db.query(HabbitTrackerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        int noOfRows = cursor.getCount();

        cursor.close();

        int confirm = db.delete(HabbitTrackerEntry.TABLE_NAME, null, null);

        if (noOfRows == confirm) {
            Toast.makeText(this, "Cleared all activites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Oops there was a problem :(" + confirm, Toast.LENGTH_SHORT).show();
        }


    }
}
