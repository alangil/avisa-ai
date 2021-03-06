package com.alangil.avisaai.view

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alangil.avisaai.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        /**
         * Animação da SplashActivity
         */
        val mContraintLayout = splash_layout
        val mAnimationDrawable = mContraintLayout.background as AnimationDrawable
        mAnimationDrawable.setEnterFadeDuration(1500)
        mAnimationDrawable.setExitFadeDuration(3500)
        mAnimationDrawable.start()

        setListeners()

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_start) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun setListeners(){
        button_start.setOnClickListener(this)
    }


}