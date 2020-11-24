package com.example.plantengine_termproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ClickLister()
    }


    private fun ClickLister(){
        waterbutton.setOnClickListener {
            val intent: Intent = Intent(this, WaterTankActivity::class.java)
            startActivity(intent)
        }

        setting.setOnClickListener {
            val intent:Intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        pumpbutton.setOnClickListener {
            val intent:Intent = Intent(this, PumpActivity::class.java)
            startActivity(intent)
        }
        ledbutton.setOnClickListener {
            val intent:Intent = Intent(this, LEDActivity::class.java)
            startActivity(intent)
        }
    }
}