#include <SoftwareSerial.h>

int A_2A = 12;

// RX, TX
SoftwareSerial softwareSerial(9, 10);

void setup() {
  Serial.begin(9600);
   softwareSerial.begin(9600);
   Serial.println("Ready");
   int Mapping=0;
   
   pinMode(A_2A, OUTPUT);
}

void loop() {
   //analogWrite(A_2A, 1023);
  // put your main code here, to run repeatedly:
  if(softwareSerial.available()){
    
    if(Serial.println(softwareSerial.read()) == 80){
      digitalWrite(A_2A, HIGH);
      //Serial.write(softwareSerial.read());
     
    }
  }
    
}

void pump(int flag){

  // 서로 값이 달라야 정방향
  // 모터가 약하면 역방향 회전이므로 서로 값을 바꿔준다.
  if(flag == 1){
   analogWrite(A_2A, 1023);
  }
}

// 둘다 LOW면 모터 멈춤 
void stop(){
  
  digitalWrite(A_2A, LOW);
}
