package com.example.plantengine_termproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_water_tank.*

class WaterTankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_tank)

        ClickLister();

    }
    private fun ClickLister(){
        home2.setOnClickListener {
            finish()
        }
        setting2.setOnClickListener {
            val intent: Intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}