package com.example.thetimetracker

object TaskContract {
    // Define table and column names
    object TaskEntry {
        const val TABLE_NAME = "tasks"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_DATE = "date"
        const val COLUMN_START_TIME = "start_time"
        const val COLUMN_END_TIME = "end_time"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_CATEGORY = "category"

    }

    // Define SQL queries for creating and deleting the table
    const val SQL_CREATE_ENTRIES = """
        CREATE TABLE ${TaskEntry.TABLE_NAME} (
            ${TaskEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${TaskEntry.COLUMN_NAME} TEXT,
            ${TaskEntry.COLUMN_DATE} TEXT,
            ${TaskEntry.COLUMN_START_TIME} TEXT,
            ${TaskEntry.COLUMN_END_TIME} TEXT,
            ${TaskEntry.COLUMN_DESCRIPTION} TEXT,
            ${TaskEntry.COLUMN_CATEGORY} TEXT
        )
    """

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TaskEntry.TABLE_NAME}"
}