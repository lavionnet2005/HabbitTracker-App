package com.example.android.habbittracker.data;

import android.provider.BaseColumns;

/**
 * Created by lkatta on 2/9/17.
 */

public final class HabbitTrackerContract {

    private HabbitTrackerContract(){

    }

    public static final class HabbitTrackerEntry implements BaseColumns{
        public static final String TABLE_NAME = "habbittracker";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ACTIVITY_NAME = "name";
        public static final String COLOUMN_ACTIVITY_TIME = "time";

    }

}
