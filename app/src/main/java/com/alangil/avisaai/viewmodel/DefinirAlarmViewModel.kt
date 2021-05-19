package com.alangil.avisaai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alangil.avisaai.service.repository.MedicineRepository
import com.alangil.avisaai.service.model.MedicineModel

class DefinirAlarmViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mMediRepository : MedicineRepository = MedicineRepository.getInstance(mContext)

    private var mSaveMedicine = MutableLiveData<Boolean>()
    val saveMedicine: LiveData<Boolean> = mSaveMedicine

    // Salva novo medicamento
    fun save(mediName: String, mediQnt: Int){
        val medicine = MedicineModel(nomeMedice = mediName,qntMedice =  mediQnt)
        mSaveMedicine.value = mMediRepository.save(medicine)
    }


}
