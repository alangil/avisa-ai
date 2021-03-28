package com.alangil.avisaai.view

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alangil.avisaai.R
import com.alangil.avisaai.viewmodel.DefinirAlarmViewModel
import kotlinx.android.synthetic.main.activity_definir_alarm.*
import java.util.*


class DefinirAlarm : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: DefinirAlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definir_alarm)

        mViewModel = ViewModelProvider(this).get(DefinirAlarmViewModel::class.java)

        setListeners()
        observe()


    }

    override fun onClick(v: View) {

        val id = v.id
        if (id == R.id.buttonSetAlarm) {

            val mediName = editMediName.text.toString()
            val mediQnt = edit_quantidade.text.toString()

            if (mediName == "" || mediQnt == "")
                Toast.makeText(this, getString(R.string.preencha_campos), Toast.LENGTH_LONG)
                    .show()
            else {

                setAlarm(mediName, mediQnt)

            }


        }
    }

    private fun observe(){
        mViewModel.saveMedicine.observe(this, androidx.lifecycle.Observer {
            if(it){
                edit_quantidade.setText("")
                editMediName.setText("")
            }else{
                Toast.makeText(applicationContext, "Algo deu errado com seus dados! :(", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setListeners() {
        buttonSetAlarm.setOnClickListener(this)
    }

    fun setAlarm(mediName: String, mediQnt: String) {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = calendar.get(Calendar.MINUTE)


        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            val timeHour: Int = hourOfDay
            val timeMinute: Int = minute

            val intent = Intent(AlarmClock.ACTION_SET_ALARM)
            intent.putExtra(AlarmClock.EXTRA_HOUR, timeHour)
            intent.putExtra(AlarmClock.EXTRA_MINUTES, timeMinute)
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, mediName)
            intent.putExtra(AlarmClock.EXTRA_DAYS, arrayListOf(1, 2, 3, 4, 5, 6, 7))
            startActivity(intent)
            Toast.makeText(applicationContext, "Novo Alarme salvo! :)", Toast.LENGTH_LONG).show()
            mViewModel.save(mediName, mediQnt.toInt())


        }, currentHour, currentMinutes, true)
        timePickerDialog.show()





    }

}