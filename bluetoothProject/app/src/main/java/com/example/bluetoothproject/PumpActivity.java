package com.example.bluetoothproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PumpActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_pump);

                ImageButton home3 = (ImageButton)findViewById(R.id.home3);
                ImageButton setting3 = (ImageButton) findViewById(R.id.setting3);

                home3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                finish();
                        }
                });

                setting3.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                                /*
                                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                                startActivity(intent);

                                 */
                        }


                });

        }
}