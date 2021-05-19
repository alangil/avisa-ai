package com.alangil.avisaai.view

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alangil.avisaai.R
import com.alangil.avisaai.viewmodel.DefinirAlarmViewModel
import kotlinx.android.synthetic.main.activity_definir_alarm.*
import java.util.*


class DefinirAlarmActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Defifir lateinit viewModel
     */
    private lateinit var mViewModel: DefinirAlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definir_alarm)


        /**
         * Remover ActionBar
         */
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        /**
         * Definir a classe para ser escutada pela viewModel
         */
        mViewModel = ViewModelProvider(this).get(DefinirAlarmViewModel::class.java)

        /**
         * Iniciar os Listeners e observers
         */
        setListeners()
        observe()


    }

    override fun onClick(v: View) {

        /**
         * Definir função ao clicar no botão Definir Alarme
         */
        val id = v.id
        if (id == R.id.buttonSetAlarm) {

            val mediName = editMediName.text.toString()
            val mediQnt = edit_quantidade.text.toString()

            /**
             * Verificação se todos os campos estão preenchidos
             */
            if (mediName == "" || mediQnt == "")
                Toast.makeText(this, getString(R.string.preencha_campos), Toast.LENGTH_LONG)
                    .show()
            /**
             * Iniciar função para criar alarmes
             */
            else {
                setAlarm(mediName, mediQnt)

            }


        }
    }

    /**
     * Oberser para verificar se tem alteração nos campos para salvar os dados do usuário
     */
    private fun observe() {
        mViewModel.saveMedicine.observe(this, {
            if (it) {
                edit_quantidade.setText("")
                editMediName.setText("")
            } else {
                Toast.makeText(
                    applicationContext,
                    "Algo deu errado com seus dados! :(",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    /**
     * Listener para botão Definir Alarme
     */
    private fun setListeners() {
        buttonSetAlarm.setOnClickListener(this)
    }

    /**
     * Intent para setar um novo Alarme utilizando AlarmClock
     */
    private fun setAlarmIntent(timeHour: Int, timeMinute: Int, mediName: String) {

        val intent = Intent(AlarmClock.ACTION_SET_ALARM)
        intent.putExtra(AlarmClock.EXTRA_HOUR, timeHour)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, timeMinute)
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, mediName)
        intent.putExtra(AlarmClock.EXTRA_DAYS, arrayListOf(1, 2, 3, 4, 5, 6, 7))
        startActivity(intent)

    }

    /**
     * Função para gerar e obter dados do timePickerDialor e chamar função para setar o(s) alarme(s)
     * com esses dados dependendo da opção selecionada no radioGroup
     */

    private fun setAlarm(mediName: String, mediQnt: String) {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinutes = calendar.get(Calendar.MINUTE)


        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            var timeHour: Int = hourOfDay
            val timeMinute: Int = minute


            var numeroAlarmes: Int = 0
            var intervalo: Int = 0


            if (radio_4.isChecked) {
                numeroAlarmes = 6
                intervalo = 4


                for (i in 1..numeroAlarmes) {


                    setAlarmIntent(timeHour, timeMinute, mediName)
                    timeHour += intervalo
                    if (timeHour > 24) timeHour -= 24
                }

            } else if (radio_6.isChecked) {
                numeroAlarmes = 4
                intervalo = 6


                for (i in 1..numeroAlarmes) {


                    setAlarmIntent(timeHour, timeMinute, mediName)
                    timeHour += intervalo
                    if (timeHour > 24) timeHour -= 24
                }

            } else if (radio_8.isChecked) {
                numeroAlarmes = 3
                intervalo = 8


                for (i in 1..numeroAlarmes) {


                    setAlarmIntent(timeHour, timeMinute, mediName)
                    timeHour += intervalo
                    if (timeHour > 24) timeHour -= 24
                }

            } else if (radio_12.isChecked) {
                numeroAlarmes = 2
                intervalo = 12


                for (i in 1..numeroAlarmes) {


                    setAlarmIntent(timeHour, timeMinute, mediName)
                    timeHour += intervalo
                    if (timeHour > 24) timeHour -= 24
                }

            } else if (radio_24.isChecked) {

                setAlarmIntent(timeHour, timeMinute, mediName)

            }


        }, currentHour, currentMinutes, true)
        timePickerDialog.show()


        /**
         * Enviar para a viewModel os dados para serem salvos no BD
         */
        mViewModel.save(mediName, mediQnt.toInt())
        Toast.makeText(
            applicationContext,
            "Novos Alarmes salvos! :)",
            Toast.LENGTH_LONG
        )
            .show()

    }

}