package com.alangil.avisaai.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alangil.avisaai.service.constants.DataBaseConstants

class MedicineDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_MEDICINE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Medicine.db"

        private const val CREATE_TABLE_MEDICINE =
            ("create table " + DataBaseConstants.MEDICINE.TABLE_NAME + " ("
                    + DataBaseConstants.MEDICINE.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.MEDICINE.COLUMNS.NAME + " text, "
                    + DataBaseConstants.MEDICINE.COLUMNS.QUANTITY + " integer);")
    }

}