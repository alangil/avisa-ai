package com.alangil.avisaai.service.repository

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.alangil.avisaai.service.constants.DataBaseConstants
import com.alangil.avisaai.service.constants.MedicineConstants
import com.alangil.avisaai.service.model.MedicineModel
import java.lang.Exception

class MedicineRepository private constructor(context: Context) {

    // Singleton

    private var mMedicineDataBaseHelper: MedicineDataBaseHelper = MedicineDataBaseHelper(context)

    companion object {

        private lateinit var repository: MedicineRepository

        fun getInstance(context: Context): MedicineRepository {
            if (!::repository.isInitialized) {
                repository = MedicineRepository(context)
            }
            return MedicineRepository(context)
        }
    }

    // Singleton

    fun save(medicine: MedicineModel): Boolean {

        return try {
            val db = mMedicineDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.MEDICINE.COLUMNS.NAME, medicine.nomeMedice)
            contentValues.put(DataBaseConstants.MEDICINE.COLUMNS.QUANTITY, medicine.qntMedice)


            db.insert(DataBaseConstants.MEDICINE.TABLE_NAME, null, contentValues)

            true
        } catch (e: Exception) {
            false
        }


    }

    fun getAll(): List<MedicineModel> {
        val list: MutableList<MedicineModel> = ArrayList()

        return try {

            val db = mMedicineDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.MEDICINE.COLUMNS.ID,
                DataBaseConstants.MEDICINE.COLUMNS.NAME,
                DataBaseConstants.MEDICINE.COLUMNS.QUANTITY
            )

            val cursor = db.query(
                DataBaseConstants.MEDICINE.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.MEDICINE.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.MEDICINE.COLUMNS.NAME))
                    val qnt =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.MEDICINE.COLUMNS.QUANTITY))

                    val medicine = MedicineModel(id, name, qnt)
                    list.add(medicine)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun get(id: Int): MedicineModel? {

        var medicine: MedicineModel? = null

        return try {
            val db = mMedicineDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.MEDICINE.COLUMNS.NAME,
                DataBaseConstants.MEDICINE.COLUMNS.QUANTITY
            )

            val selection = DataBaseConstants.MEDICINE.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.MEDICINE.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if(cursor != null && cursor.count > 0){
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.MEDICINE.COLUMNS.NAME))
                val qnt =
                    cursor.getInt(cursor.getColumnIndex(DataBaseConstants.MEDICINE.COLUMNS.QUANTITY))

                medicine = MedicineModel(id, name, qnt)

            }

            cursor?.close()

            medicine
        } catch (e: Exception) {
            medicine
        }

    }


    fun update(medicine: MedicineModel): Boolean {

        return try {
            val db = mMedicineDataBaseHelper.writableDatabase

            val contentValues = ContentValues()
            contentValues.put(DataBaseConstants.MEDICINE.COLUMNS.NAME, medicine.nomeMedice)
            contentValues.put(DataBaseConstants.MEDICINE.COLUMNS.QUANTITY, medicine.qntMedice)

            val selection = DataBaseConstants.MEDICINE.COLUMNS.ID + " = ?"
            val args = arrayOf(medicine.id.toString())

            db.update(DataBaseConstants.MEDICINE.TABLE_NAME, contentValues, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(medicine: MedicineModel): Boolean {

        return try {
            val db = mMedicineDataBaseHelper.writableDatabase

            val selection = DataBaseConstants.MEDICINE.COLUMNS.NAME + " = ?"
            val args = arrayOf(medicine.nomeMedice)

            db.delete(DataBaseConstants.MEDICINE.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }

    }
}