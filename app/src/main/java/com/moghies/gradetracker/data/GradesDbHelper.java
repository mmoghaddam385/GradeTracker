package com.moghies.gradetracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite open helper for grades db
 *
 * Created by mmogh on 12/22/2017.
 */

public class GradesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grades.db";
    private static final int DATABASE_VERSION = 1;

    public GradesDbHelper(Context context) {
        this(context, DATABASE_NAME);
    }

    /**
     * package private Ctor with specified DB name only for testing purposes
     */
    GradesDbHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GradesContract.GPAContainerEntry.SQL_CREATE_STATEMENT);
        sqLiteDatabase.execSQL(GradesContract.AssignmentEntry.SQL_CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        deleteAll(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    /**
     * Drop Everything!
     */
    public void deleteAll(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GradesContract.AssignmentEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GradesContract.GPAContainerEntry.TABLE_NAME);
    }
}
