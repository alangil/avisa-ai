package com.alangil.avisaai.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alangil.avisaai.R
import com.alangil.avisaai.listener.MedicineListener
import com.alangil.avisaai.service.model.MedicineModel

class MedicineViewHolder(itemView: View, private val listener: MedicineListener): RecyclerView.ViewHolder(itemView) {

    fun bind(medicine: MedicineModel){
        val nomeRemedio = itemView.findViewById<TextView>(R.id.text_nome_remedio)
        nomeRemedio.text = (medicine.nomeMedice + " : ")

        val quantidadeRemedio = itemView.findViewById<TextView>(R.id.text_qnt_remedios)
        quantidadeRemedio.text = medicine.qntMedice.toString()

        nomeRemedio.setOnClickListener {
            listener.onClick(medicine.id)
        }

        nomeRemedio.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remove_medicine)
                .setMessage(R.string.wish_remove)
                .setPositiveButton(R.string.remove){ dialog, which ->
                    listener.onDelete(medicine.id)
                }
                .setNeutralButton(R.string.cancel, null)
                .show()

            true
        }


    }

}