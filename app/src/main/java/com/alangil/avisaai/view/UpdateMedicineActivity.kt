package com.alangil.avisaai.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alangil.avisaai.R
import com.alangil.avisaai.service.constants.MedicineConstants
import com.alangil.avisaai.viewmodel.UpdateMedicineViewModel
import kotlinx.android.synthetic.main.activity_update_medicine.*

class UpdateMedicineActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: UpdateMedicineViewModel
    private var mMedicineId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_medicine)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mViewModel = ViewModelProvider(this).get(UpdateMedicineViewModel::class.java)

        setListeners()
        observe()
        loadData()

    }

    private fun setListeners(){
        button_save_update.setOnClickListener(this)
    }

    private fun observe(){
        mViewModel.medicine.observe(this, {
            edit_medi_name_update.setText(it.nomeMedice)
        })
    }

    private fun loadData(){
        val bundle = intent.extras
        if(bundle != null){

            mMedicineId = bundle.getInt(MedicineConstants.MEDICINEID)
            mViewModel.load(mMedicineId)

        }
    }



    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.button_save_update){

            val mediName = edit_medi_name_update.text.toString()
            val mediQnt = edit_quantidade_update.text.toString()

            if (mediName == "" || mediQnt == "")
                Toast.makeText(this, getString(R.string.preencha_campos), Toast.LENGTH_LONG)
                    .show()
            else {

                mViewModel.update(mMedicineId, mediName, mediQnt.toInt())
                finish()

            }

        }
    }

}