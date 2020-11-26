package com.example.plantengine_termproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_l_e_d.*
import kotlinx.android.synthetic.main.activity_water_tank.*


class LEDActivity : AppCompatActivity() {
    var timerset = arrayOf<String>("선택", "1시간", "2시간", "3시간", "4시간", "5시간", "6시간", "7시간", "8시간", "9시간", "10시간", "11시간", "12시간")
    var timeSetting: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l_e_d)

        spinner1.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, timerset)
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                if (p0 != null) {
                    (p0.getChildAt(0) as TextView).setTextColor(Color.BLACK)
                }
                Toast.makeText(this@LEDActivity, "조명시간을 설정해 주세요", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, Position: Int, p3: Long) {
                if (p0 != null) {
                    (p0.getChildAt(0) as TextView).setTextColor(Color.BLACK)
                }
                if(!timerset[Position].equals("선택")) {
                    Log.i("####", "aaaa" + timerset[Position]);
                    Log.i("####", "aaaa");
                    timeSetting = timerset[Position]
                }
            }//end of onItemSelected()
        }//end of onItemSelectedListener


        ClickLister();
    }//end of onCreate()

    private fun ClickLister(){
        home4.setOnClickListener {
            finish()
        }
        setting4.setOnClickListener {
            val intent: Intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        ledOn.setOnClickListener {
            if(timeSetting.equals(null)){
                Toast.makeText(
                    this@LEDActivity,
                     "조명시간 설정을 해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                Toast.makeText(
                    this@LEDActivity,
                    timeSetting + "동안 조명이 켜집니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }//end of ledOn.setOnclickListener()

        ledOff.setOnClickListener {
            Toast.makeText(
                this@LEDActivity,
                 "조명이 꺼졌습니다.",
                Toast.LENGTH_SHORT
            ).show()
        }//end of ledOn.setOnclickListener()
    }//end of ClickLister()
}