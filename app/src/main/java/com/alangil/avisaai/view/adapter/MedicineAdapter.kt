package com.alangil.avisaai.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alangil.avisaai.R
import com.alangil.avisaai.listener.MedicineListener
import com.alangil.avisaai.service.model.MedicineModel
import com.alangil.avisaai.view.viewholder.MedicineViewHolder

class MedicineAdapter: RecyclerView.Adapter<MedicineViewHolder>() {

    // Lista de medicamentos
    private var mMedicineList: List<MedicineModel> = arrayListOf()
    private lateinit var mListener: MedicineListener

    /**
     * Faz a criação do layout da linha
     * Faz a criação de várias linhas que vão mostrar cada um dos medicamentos
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
       val item = LayoutInflater.from(parent.context).inflate(R.layout.row_medicine,parent,false)
        return MedicineViewHolder(item, mListener)
    }

    /**
     * Para cada linha, este método é chamado
     * É responsável por atribuir os valores de cada item para uma linha específica
     */
    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(mMedicineList[position])
    }

    /**
     * Define o tamanho da RecyclerView
     */
    override fun getItemCount(): Int {
        return mMedicineList.count()
    }

    /**
     * Atualização da lista de medicamentos
     */
    fun updateMedicine(list: List<MedicineModel>){
        mMedicineList = list
        notifyDataSetChanged()
    }

    /**
     * Eventos da listagem
     */
    fun attachListener(listener: MedicineListener){
        mListener = listener
    }

}