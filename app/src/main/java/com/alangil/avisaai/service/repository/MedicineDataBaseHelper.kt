package com.alangil.avisaai.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alangil.avisaai.service.constants.DataBaseConstants

class MedicineDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * Método executado somente uma vez quando o acesso ao banco de dados é feito pela primeira vez
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_MEDICINE)
    }

    /**
     * Método executado quando a versão do DATABASE_VERSION é alterada
     * Dessa maneira, a aplicação sabe que o banco de dados foi alterado e é necessário rodar o script de update
     */
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