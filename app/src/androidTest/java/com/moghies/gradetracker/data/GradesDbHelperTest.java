package com.moghies.gradetracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for GradesDbHelperTest
 *
 * Created by mmogh on 12/22/2017.
 */

@RunWith(AndroidJUnit4.class)
public class GradesDbHelperTest {
    private static final String TEST_DATABASE_NAME = "test.db";

    @Test
    public void testCreateDestroy() {
        Context context = InstrumentationRegistry.getTargetContext();
        GradesDbHelper dbHelper = new GradesDbHelper(context, TEST_DATABASE_NAME);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.deleteAll(db);
    }

}
