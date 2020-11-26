package com.example.plantengine_termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pump.*
import kotlinx.android.synthetic.main.activity_water_tank.*
import java.lang.Thread.sleep
import java.util.*
import kotlin.concurrent.schedule

class PumpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump)

        ClickLister();
    }
    private fun ClickLister(){
        home3.setOnClickListener {
            finish()
        }
        setting3.setOnClickListener {
            val intent: Intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}