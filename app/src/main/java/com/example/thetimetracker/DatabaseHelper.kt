package com.example.thetimetracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TaskContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(TaskContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun insertTask(task: Task): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_NAME, task.name)
            put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.description)
            put(TaskContract.TaskEntry.COLUMN_START_TIME, task.startTime)
            put(TaskContract.TaskEntry.COLUMN_END_TIME, task.endTime)
            put(TaskContract.TaskEntry.COLUMN_DATE, task.date)
            put(TaskContract.TaskEntry.COLUMN_CATEGORY, task.category)
        }
        val newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values)
        db.close()
        return newRowId
    }

    fun readAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val db = readableDatabase
        val projection = arrayOf(
            TaskContract.TaskEntry.COLUMN_ID,
            TaskContract.TaskEntry.COLUMN_NAME,
            TaskContract.TaskEntry.COLUMN_DESCRIPTION,
            TaskContract.TaskEntry.COLUMN_START_TIME,
            TaskContract.TaskEntry.COLUMN_END_TIME,
            TaskContract.TaskEntry.COLUMN_DATE,
            TaskContract.TaskEntry.COLUMN_CATEGORY
        )

        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME))
                val description = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION))
                val startTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_START_TIME))
                val endTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_END_TIME))
                val date = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DATE))
                val category = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_CATEGORY))
                tasks.add(Task(id, name, description, startTime, endTime, date, category))
            }
        }
        cursor.close()
        db.close()
        return tasks
    }

    fun deleteTask(id: Int): Int {
        val db = writableDatabase
        val selection = "${TaskContract.TaskEntry.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id.toString())
        val deletedRows = db.delete(TaskContract.TaskEntry.TABLE_NAME, selection, selectionArgs)
        db.close()
        return deletedRows
    }

    fun updateTask(task: Task): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_NAME, task.name)
            put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.description)
            put(TaskContract.TaskEntry.COLUMN_START_TIME, task.startTime)
            put(TaskContract.TaskEntry.COLUMN_END_TIME, task.endTime)
            put(TaskContract.TaskEntry.COLUMN_DATE, task.date)
            put(TaskContract.TaskEntry.COLUMN_CATEGORY, task.category)
        }
        val selection = "${TaskContract.TaskEntry.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(task.id.toString())
        val updatedRows = db.update(TaskContract.TaskEntry.TABLE_NAME, values, selection, selectionArgs)
        db.close()
        return updatedRows
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Task.db"
    }
}