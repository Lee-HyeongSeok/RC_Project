package com.example.plantengine_termproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pump.*
import java.lang.Thread.sleep
import java.util.*
import kotlin.concurrent.schedule

class PumpActivity : AppCompatActivity() {
    private var isClicked = false
    private var count = 0
    private var rotate = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump)




    }
}