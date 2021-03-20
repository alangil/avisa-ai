package com.alangil.avisaai

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_definir_alarm.*
import java.util.*


class DefinirAlarm : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definir_alarm)


        buttonSetAlarm.setOnClickListener(this)
        //timePicker.setOnClickListener(this)


    }

    override fun onClick(v: View) {

        val id = v.id
        //if (id == R.id.timePicker) setTime()
        if (id == R.id.buttonSetAlarm) setAlarm()


    }

    //private fun setTime() {


    //}

    private fun setAlarm() {

        if (editMediName.text.toString() == "")
        Toast.makeText(this, getString(R.string.preencha_nome_remedio), Toast.LENGTH_LONG).show()
        else {

            val calendar = Calendar.getInstance();
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentMinutes = calendar.get(Calendar.MINUTE)


            val timePickerDialog: TimePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val timeHour = hourOfDay
                val timeMinute = minute

                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, timeHour)
                intent.putExtra(AlarmClock.EXTRA_MINUTES, timeMinute)
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, editMediName.text.toString())
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Não existe nenhum App que suporte essa ação!", Toast.LENGTH_LONG).show()
                }
            }, currentHour, currentMinutes, true)
            timePickerDialog.show()



        }

    }
}