package com.alangil.avisaai.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alangil.avisaai.service.model.MedicineModel
import com.alangil.avisaai.service.repository.MedicineRepository

class UpdateMedicineViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mMediRepository: MedicineRepository = MedicineRepository.getInstance(mContext)

    /**
     * update
     */

    private var mSaveMedicine = MutableLiveData<Boolean>()
    val saveMedicine: LiveData<Boolean> = mSaveMedicine

    fun update(id: Int, mediName: String, mediQnt: Int){
        val medicine = MedicineModel(id, nomeMedice = mediName,qntMedice =  mediQnt)
        mSaveMedicine.value = mMediRepository.update(medicine)
    }

    /**
     * load
     */

    private var mMedicine = MutableLiveData<MedicineModel>()
    val medicine: LiveData<MedicineModel> = mMedicine

    fun load(id: Int){
        mMedicine.value = mMediRepository.get(id)
    }

}