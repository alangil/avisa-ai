package com.alangil.avisaai.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import com.alangil.avisaai.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        setListeners()

    }

    /**
     * Definição dos botões
     */
    override fun onClick(v: View) {

        val id = v.id

        if (id == button_set_new.id) {
            startActivity(Intent(this, DefinirAlarmActivity::class.java))
        }
        if (id == R.id.button_change) {
            abrirAlarmes()
        }

        if(id == R.id.button_all_medicines){
            startActivity(Intent(this, AllMedicinesActivity::class.java))
        }

    }

    private fun setListeners(){

        button_set_new.setOnClickListener(this)
        button_change.setOnClickListener(this)
        button_all_medicines.setOnClickListener(this)

    }

    private fun abrirAlarmes() {
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        if (intent.resolveActivity(packageManager) != null) {

            startActivity(intent)


        } else {

            Toast.makeText(
                this,
                getString(R.string.nao_existe_app_suporte),
                Toast.LENGTH_LONG
            ).show()
        }

    }
}