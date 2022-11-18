package com.example.contentprovider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(context: Context?) : SQLiteOpenHelper(context,"ADB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ATABLE(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESIGNATION TEXT)")
        db?.execSQL("INSERT INTO ATABLE(NAME,DESIGNATION) VALUES('VISHAL','AOSP Developer')")
        db?.execSQL("INSERT INTO ATABLE(NAME,DESIGNATION) VALUES('ASHISH','AOSP Intern Developer')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
          }
}
