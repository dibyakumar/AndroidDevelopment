package com.example.a7minworkoutchallenge

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context,factory:SQLiteDatabase.CursorFactory?):
SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "7minworkoutchallenege.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_HISTORY = "history"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_COMPLTED_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_contact_table = ("CREATE TABLE "+ TABLE_HISTORY+"("+ COLUMN_ID+" INTEGER PRIMERY KEY,"+ COLUMN_COMPLTED_DATE+" TEXT"+")")

        db!!.execSQL(create_contact_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS"+ TABLE_HISTORY)
        onCreate(db)
    }

    fun addDate(date:String){
        val values = ContentValues()
        values.put(COLUMN_COMPLTED_DATE,date)
        val db = this.writableDatabase
        db.insert(TABLE_HISTORY,null,values)
        db.close()
    }
    fun getAllData():ArrayList<String>{
        val list = ArrayList<String>()
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY",null)

        while (cursor.moveToNext()){
            val dateValue = (cursor.getString(cursor.getColumnIndex(COLUMN_COMPLTED_DATE)))
            list.add(dateValue)
        }
        cursor.close()
        return list
    }
}