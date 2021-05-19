package com.alangil.avisaai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alangil.avisaai.service.model.MedicineModel
import com.alangil.avisaai.service.repository.MedicineRepository

class AllMedicinesViewModel(application: Application): AndroidViewModel(application) {

    private val mMedicineRepository = MedicineRepository.getInstance(application.applicationContext)

    private val mMedicineList = MutableLiveData<List<MedicineModel>>()
    val medicineList: LiveData<List<MedicineModel>> = mMedicineList

    // Carrega medicamentos
    fun load(){
        mMedicineList.value = mMedicineRepository.getAll()
    }

    // Deleta medicamento
    fun delete(id: Int) {
        mMedicineRepository.delete(id)
    }
}