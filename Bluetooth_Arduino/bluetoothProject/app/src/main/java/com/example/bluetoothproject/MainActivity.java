package com.example.bluetoothproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {
    ImageButton WaterBtn, SettingBtn, PumpBtn, LedBtn, ChatBtn;
    private BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 블루투스 객체 선언
        bt = new BluetoothSPP(this);

        if(!bt.isBluetoothAvailable()){
            Toast.makeText(getApplicationContext(),
                    "Bluetooth is not available",
                    Toast.LENGTH_SHORT).show();
            finish();
        }

        WaterBtn = (ImageButton)findViewById(R.id.waterbutton);
        SettingBtn = (ImageButton) findViewById(R.id.setting);
        PumpBtn = (ImageButton)findViewById(R.id.pumpbutton);
        LedBtn = (ImageButton) findViewById(R.id.ledbutton);
        ChatBtn = (ImageButton) findViewById(R.id.chat);

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener(){
            public void onDataReceived(byte[] data, String message){
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener(){
            public void onDeviceConnected(String name, String address){
                Toast.makeText(getApplicationContext(),
                        "connected to"+name+"\n"+address,
                        Toast.LENGTH_SHORT).show();
            }
            public void onDeviceDisconnected(){
                Toast.makeText(getApplicationContext(),
                        "connection lost", Toast.LENGTH_SHORT).show();
            }
            public void onDeviceConnectionFailed(){
                Toast.makeText(getApplicationContext(),
                        "unable to connect", Toast.LENGTH_SHORT).show();
            }
        });
        WaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WaterTankActivity.class);
                startActivity(intent);
            }
        });

        ChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bt.getServiceState() == BluetoothState.STATE_CONNECTED){
                    bt.disconnect();
                }else{
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
                /*
                Intent intent2 = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent2);
                 */
            }
        });

        SettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent4);
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
    public void onDestroy(){
        super.onDestroy();
        bt.stopService();
    }
    public void onStart(){
        super.onStart();
        if(!bt.isBluetoothEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        }else{
            if(!bt.isServiceAvailable()){
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }
    public void setup(){
        // 데이터 전송
        // bt.send() 로 데이터 전송
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}