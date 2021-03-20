package com.alangil.avisaai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Avisa Aí! ;)")

        button_login.setOnClickListener(this)

    }

    override fun onClick(v: View) {

        var id = v.id

        if(id == button_login.id){
            handleSave()
        }

    }

    private fun handleSave(){

        val name = edit_name.text.toString()

        if ( name != ""){
            startActivity(Intent(this, DefinirAlarm::class.java ))
        }
        else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }

    }
}