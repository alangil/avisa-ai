package com.alangil.avisaai.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alangil.avisaai.R
import com.alangil.avisaai.listener.MedicineListener
import com.alangil.avisaai.service.model.MedicineModel
import com.alangil.avisaai.view.viewholder.MedicineViewHolder

class MedicineAdapter: RecyclerView.Adapter<MedicineViewHolder>() {

    private var mMedicineList: List<MedicineModel> = arrayListOf()
    private lateinit var mListener: MedicineListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
       val item = LayoutInflater.from(parent.context).inflate(R.layout.row_medicine,parent,false)
        return MedicineViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(mMedicineList[position])
    }

    override fun getItemCount(): Int {
        return mMedicineList.count()
    }

    fun updateMedicine(list: List<MedicineModel>){
        mMedicineList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: MedicineListener){
        mListener = listener
    }

}