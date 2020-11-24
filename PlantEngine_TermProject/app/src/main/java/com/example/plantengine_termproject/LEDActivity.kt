package com.example.plantengine_termproject

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


class LEDActivity : AppCompatActivity() {
    var timerset = arrayOf<String>("선택", "1시간", "2시간", "3시간", "4시간", "5시간", "6시간", "7시간", "8시간", "9시간", "10시간", "11시간", "12시간")

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
                Toast.makeText(this@LEDActivity, timerset[Position] + "으로 설정 하였습니다.", Toast.LENGTH_SHORT).show()
                Log.i("####","aaaa"+timerset[Position]);
                Log.i("####","aaaa");
                if (p0 != null) {
                    (p0.getChildAt(0) as TextView).setTextColor(Color.BLACK)
                }
            }
        }
    }
}