package com.moghies.gradetracker.data;

import android.provider.BaseColumns;

/**
 * The contract that the SQLite db will abide by
 *
 * Created by mmogh on 12/22/2017.
 */

public final class GradesContract {

    private GradesContract() { }

    /**
     * A GPAContainer represents anything that has a GPA associated with it in a hierarchical fashion
     */
    public static final class GPAContainerEntry implements BaseColumns {
        public static final String TABLE_NAME = "gpa_container";

        public static final String COLUMN_NAME = "name"; // string name
        public static final String COLUMN_TYPE = "type"; // GPAContainerType enum index
        public static final String COLUMN_PARENT = "parent"; // fkey referring to parent container or NULL

        public static final String SQL_CREATE_STATEMENT =
            "CREATE TABLE " + TABLE_NAME + " (" +
                _ID           + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME   + " TEXT NOT NULL, " +
                COLUMN_TYPE   + " INTEGER NOT NULL, " +
                COLUMN_PARENT + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_PARENT + ") " +
                "REFERENCES " + TABLE_NAME + "(" + _ID + ")" +
            "); ";

        /**
         * Different types of GPA containers
         */
        public enum GPAContainerType {
            INSTITUTION,
            SEMESTER,
            COURSE
        }
    }

    /**
     * Assignments are the lowest levels of GPAContainers that GPAContainters use to calcaulate GPA
     */
    public static final class AssignmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "assignment";

        public static final String COLUMN_NAME = "name"; // string name
        public static final String COLUMN_MAX_POINTS = "max_points"; // double, not null, max points assignment is out of
        public static final String COLUMN_EARNED_POINTS = "earned_points"; // double, nullable, earned points on assignment
        public static final String COLUMN_CATEGORY = "category"; // string, not null, user defined category for assignment
        public static final String COLUMN_COURSE = "course"; // fkey referring to GPAContainer that owns this

        public static final String SQL_CREATE_STATEMENT =
            "CREATE TABLE " + TABLE_NAME + " (" +
                _ID                  + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME          + " TEXT NOT NULL, " +
                COLUMN_MAX_POINTS    + " REAL NOT NULL, " +
                COLUMN_EARNED_POINTS + " REAL, " +
                COLUMN_CATEGORY      + " TEXT NOT NULL, " +
                COLUMN_COURSE        + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COLUMN_COURSE + ") " +
                "REFERENCES " + GPAContainerEntry.TABLE_NAME + "(" + GPAContainerEntry._ID + ")" +
            "); ";
    }
}
