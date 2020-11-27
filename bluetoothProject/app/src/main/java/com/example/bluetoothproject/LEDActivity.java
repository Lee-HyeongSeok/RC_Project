package com.example.bluetoothproject;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class LEDActivity extends AppCompatActivity {

    private String[] timerest = new String[]{"선택", "1시간", "2시간", "3시간", "4시간", "5시간", "6시간", "7시간", "8시간", "9시간", "10시간", "11시간", "12시간"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_e_d);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, timerest);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), timerest[i] + "이 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
                Log.i("####", "aaaa");
                if (adapterView != null) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (adapterView != null) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }
                Toast.makeText(getApplicationContext(), "조명시간을 선택해주세요",
                        Toast.LENGTH_SHORT).show();
            }


        });
    }
}