package com.example.plantengine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton WaterBtn, SettingBtn, PumpBtn, LedBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WaterBtn = (ImageButton)findViewById(R.id.waterbutton);
        SettingBtn = (ImageButton) findViewById(R.id.setting);
        PumpBtn = (ImageButton)findViewById(R.id.pumpbutton);
        LedBtn = (ImageButton) findViewById(R.id.ledbutton);

        WaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WaterTankActivity.class);
                startActivity(intent);
            }
        });

        SettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent2);
            }
        });

        PumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), PumpActivity.class);
                startActivity(intent3);
            }
        });

        LedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(), LEDActivity.class);
                startActivity(intent4);
            }
        });
    }
}