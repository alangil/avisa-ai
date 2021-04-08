package com.alangil.avisaai.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alangil.avisaai.R
import com.alangil.avisaai.listener.MedicineListener
import com.alangil.avisaai.service.constants.MedicineConstants
import com.alangil.avisaai.view.adapter.MedicineAdapter
import com.alangil.avisaai.viewmodel.AllMedicinesViewModel
import kotlinx.android.synthetic.main.activity_all_medicines.*

class AllMedicinesActivity : AppCompatActivity() {

    private lateinit var mAllMecidinesViewModel: AllMedicinesViewModel
    private val mAdapter: MedicineAdapter = MedicineAdapter()
    private lateinit var mListener: MedicineListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_medicines)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        mAllMecidinesViewModel = ViewModelProvider(this).get(AllMedicinesViewModel::class.java)

        //RecyclerView
        // 1 = Obter a recycler
        val recycler = recycler_all_medicines

        // 2 = Definir um layout
        recycler.layoutManager = LinearLayoutManager(applicationContext)

        //3 = Definir um adapter (Juntar elementos de layout e dados)
        recycler.adapter = mAdapter

        mListener = object : MedicineListener{
            override fun onClick(id: Int) {
                val intent = Intent(applicationContext, UpdateMedicineActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(MedicineConstants.MEDICINEID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mAllMecidinesViewModel.delete(id)
                mAllMecidinesViewModel.load()
            }
        }

        mAdapter.attachListener(mListener)

        observer()

    }

    override fun onResume() {
        super.onResume()
        mAllMecidinesViewModel.load()
    }

    private fun observer() {
        mAllMecidinesViewModel.medicineList.observe(this, {
            mAdapter.updateMedicine(it)
        })
    }
}