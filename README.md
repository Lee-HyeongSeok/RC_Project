# RC_Project
RC 창의 도전 프로젝트   
#### 보드 : 아두이노 우노   

#### * ESP-8266 WiFi 모듈   

#### 스케치 목록   
		1. sketch_sep23a.ino : ESP8266 펌웨어 업데이트 이 후 AT 통신 테스트 코드   

		2. sketch_sep25a.ino : 
				- hercules tcp server(port : 23)을 열어서 우노 보드의 ESP8266과 연결   
				- tcp server에서 명령어 전달   
				- 우노 보드의 ESP8266에서 명령어를 받아서 우노 보드 내장 LED를 제어   


#### * AT 명령어 모음   

| AT 명령어 | 내용 |   
| :---: | :---: |   
| AT | ESP8266이 정상적으로 동작하고 있음을 나타낸다. |   
| AT+RST | 모듈 리셋 명령 |   
| AT+GMR | 모듈 버전 확인 명령 |   
| AT+CWMODE | WiFi 모드 확인 또는 설정 명령 1. WiFi 디바이스 기능 2. Access Point 기능 3. 디바이스+AP 기능 |   
| AT+CWLAP | AP 리스트 출력 |   
| AT+CWJAP | AP 접속 명령 (AT+CWJAP="ssid","pass","채널ID","보안방식") |   
| AT+CWLIF | 할당받은 IP 주소 출력 |   
| AT+CIPSTA | IP 어드레스 설정(Station Mode) |   
| AT+CIPAP | IP 어드레스 설정(AP Mode) |   
| AT+CIPSTATUS | 접속 정보 |   
| AT+CIPSTART | TCP, UDP 접속 해제 |   
| AT+CIPCLOSE | TCP, UDP 접속 해제 |   
| AT+CIPSEND | 데이터 전송 |   
| AT+FSR | 로컬 IP 취득 |   
| AT+CIPSERVER | 서버 설정 |   
| AT+CIPMODE | 전송모드 설정 |   

* MySQL in Apache Server <-> php <-> Android 연결 구도   
		1. xampp tool을 사용하여 Apache 서버 구동   
		2. xampp tool에서 제공하는 MySQL 사용   
		3. MySQL에 존재하는 데이터베이스를 php 프레임워크로 접근   
		4. Android에서 Json parsing을 통해 php 프레임워크에 접근하여 데이터 삽입, 수정, 삭제 등의 연산   
		5. php_connection.php 파일 제공   
		
		```php
		<?php
		// apache server 명, MySQL 관리자 이름, MySQL 패스워드, 접근하려는 데이터 베이스 이름 
		$con = mysqli_connect("localhost", "root", "", "test");

		// mysql에 지정한 url을 통해 연결 시도 
		if(mysqli_connect_errno($con)){
			echo "Failed to connect to MySql : " . mysqli_connect_error();
		}

		// mysql에 연결 시도 성공 시 문자열 셋을 utf-8로 설정 
		mysqli_set_charset($con, "utf8");

		// MySQL에 쿼리문을 전송하기 위해 변수 설정 
		// MySQL Url을 지정했던 변수와 함께 쿼리문을 인자로 전달 
		$res = mysqli_query($con, "select * from member");
		$result = array(); // 쿼리문에 대한 결과를 array()로 저장하기 위해 array()형 변수 선언 

		// array()형 변수를 순회 
		while($row = mysqli_fetch_array($res)){
			// select * from member에 대한 결과 값을 array에 삽입
			array_push($result,
				array('name'=>$row['name'], 'id'=>$row['id'], 'pass'=>$row['pass']));
		}
	
		// select * from member 결과에 대한 array()형 변수를 json 형태로 인코딩
		// Android에서 parsing 하기 위함 
		echo json_encode(array("result"=>$result));
		mysqli_close($con); // 사용 후 MySQL Connection은 연결을 끊어줌 
			
		?>
		```


#### Andriod Studio && Arduino Bluetooth Connection   
		* 주의사항   
				1. 우선 안드로이드 휴대폰으로 블루투스 모듈과 연결이 되는지 확인해본다.   
				2. 연결이 안된다면 블루투스를 초기화 시킨다.   
				3. 블루투스 이름, 핀, 통신속도 순으로 초기화 진행   
					AT+NAME(사용할 이름), AT+PIN(사용할 핀번호), AT+BAUD4   
				
```arduino
// 시리얼 통신을 통해서 블루투스 초기화
#include <SoftwareSerial.h>

// RX, TX
SoftwareSerial softwareSerial(2, 3);
char data;

void setup() {
  Serial.begin(9600);
   softwareSerial.begin(9600);
   Serial.println("Ready");
}

void loop() {
   if(softwareSerial.available()){
    Serial.write(softwareSerial.read());
   }
   if(Serial.available()){
    softwareSerial.write(Serial.read());
   }
}
```   

		* Android Studio app 수준의 gradle 파일의 최하단에 dependencies{ ... }에 추가   
				* implementation 'com.akexorcist:bluetoothspp:1.0.0'   

#### Android Studio BluetoothSPP 코드   
		* 연결을 누르면 통신 가능한 블루투스 기기를 찾음   
				* 아무것도 뜨지 않는다면 위에 주의사항 필독   
		* 전송을 누르면 "Text"라는 문자열을 아두이노에 전송되고 시리얼 모니터에 출력됨   

```java   
package com.example.bluetoothproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {

    private BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = new BluetoothSPP(this);

        if(!bt.isBluetoothAvailable()){
            Toast.makeText(getApplicationContext(), "bluetooth is not available", Toast.LENGTH_SHORT).show();
            finish();
        }

        // 아두이노에서 넘어오는 데이터를 수신하는 부분
        // 1바이트씩 넘어옴
        // data에 아두이노에서 온 데이터를 넣어 바이트를 모두 합친 후 msg를 통해 리턴됨
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener(){
            public void onDataReceived(byte[] data, String msg){
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener(){
            public void onDeviceConnected(String name, String address){
                Toast.makeText(getApplicationContext(), "Connected to " + name+"\n"+address, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeviceDisconnected() {
                Toast.makeText(getApplicationContext(), "Connection lost", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnConnect = findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(bt.getServiceState() == BluetoothState.STATE_CONNECTED){
                    bt.disconnect();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
    }

    public void onDestroy(){
        super.onDestroy();
        bt.stopService();
    }
    public void onStart(){
        super.onStart();

        if(!bt.isBluetoothAvailable()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        }else{
            if(!bt.isServiceAvailable()){
                bt.setupService();
                // 아두이노와 같은 기기들과 연결할 때 사용하는 것
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }

    // 블루투스 서비스 시작 후 실행되는 부분
    // 전송 버튼 누르면 Text라는 글자가 아두이노에 전송됨
    public void setup(){
        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                bt.send("Text", true);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK) {
                bt.connect(data);
            }
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth was not enabled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
```   

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <Button
        android:id="@+id/btnConnect"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="연결"
         />

    <Button
        android:id="@+id/btnSend"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="보내기"
        />


</LinearLayout>
```

#### 최종 순서도   

![Untitled Diagram](https://user-images.githubusercontent.com/55940552/104094565-fe6c8000-52d4-11eb-8651-c098456e2aff.png)   
