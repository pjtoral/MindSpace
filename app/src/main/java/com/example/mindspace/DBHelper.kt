package com.example.mindspace

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION =1
        private const val DATABASE_NAME = "mh.db"
        private const val TBL_MH = "tbl_mh"
        private const val  ID = "id"
        private const val ENTRY = "entry"
        private const val DATE = "date"
        private const val MOOD = "mood"
        private const val NUM = "num"

    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTbl = ("CREATE TABLE " + TBL_MH + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " TEXT,"
                + ENTRY + " TEXT," + MOOD + " TEXT," + NUM + " INTEGER" + ")")
        db.execSQL(createTbl)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TBL_MH")
        onCreate(db)
    }

    fun insertEntry(entry: DiaryEntry): Long
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DATE, entry.date)
        contentValues.put(ENTRY, entry.entry)
        contentValues.put(MOOD, entry.mood)
        contentValues.put(NUM, entry.num)
        val success = db.insert(TBL_MH, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllEntries(): ArrayList<DiaryEntry>{
        val entryList: ArrayList<DiaryEntry> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_MH"
        val db = this.readableDatabase

        val cursor : Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch(e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var date: String
        var entry: String
        var mood: String
        var num : Int
        if(cursor.moveToFirst()){
            do{
                date = cursor.getString(cursor.getColumnIndex("date"))
                entry = cursor.getString(cursor.getColumnIndex("entry"))
                mood = cursor.getString(cursor.getColumnIndex("mood"))
                num = cursor.getInt(cursor.getColumnIndex("num"))
                val enter = DiaryEntry( date = date, entry = entry, mood = mood, num = num)
                entryList.add(enter)

            }while(cursor.moveToNext())
        }

        return entryList
    }
}