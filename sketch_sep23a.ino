#include <SoftwareSerial.h>

SoftwareSerial ESPserial(2, 3); // rx, tx

void setup() {
 
  Serial.begin(9600);
  ESPserial.begin(9600);
  ESPserial.setTimeout(9000);
  delay(1000);
  
}

void loop() {
  
  if(ESPserial.available()){
    Serial.write(ESPserial.read());
  }
  if(Serial.available()){
    ESPserial.write(Serial.read());
  }
  
}
