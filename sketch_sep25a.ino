#include "ESP8266.h"
#include <SoftwareSerial.h>

#define SSID        "508-2.4G" /* SSID, 와이파이 이름 */
#define PASSWORD    "kpu123456!" /* 와이파이 비밀번호 */
#define HOST_NAME   "172.30.1.40" /*현재 pc의 공유기로부터 할당받은 IP주소*/
#define HOST_PORT   (23) /* 툴에서 설정한 tcp 서버포트번호*/

SoftwareSerial mySerial(2, 3); /* RX:D3, TX:D2 */
ESP8266 wifi(mySerial); /* ESP8266 객체 */

void printUsage(){
  /* tcp 서버에 접속 시 보여줄 메시지 */
  uint8_t buf[] = "Usage\n1 : Turn on LED\n2 : Turn off LED\nS : LEF status\n\n";
  wifi.send(buf, strlen(buf)); /* ESP8266 객체의 send 함수를 사용하여 buf(메시지) 보내기 */
}

bool isConnected = false; /* tcp 서버와 연결된 여부 */

void setup(void)
{
    Serial.begin(9600); /* 시리얼 통신 시작 속도 */
    Serial.print("setup begin\r\n");
    
    Serial.print("FW Version:");
    Serial.println(wifi.getVersion().c_str()); /* ESP8266 객체의 버전을 받아와서 char형 문자열로 바꿔줌*/
      
    if (wifi.setOprToStationSoftAP()) { /* 공유기의 동작 여부 검사 */
        Serial.print("to station + softap ok\r\n");
    } else {
        Serial.print("to station + softap err\r\n");
    }
 
    if (wifi.joinAP(SSID, PASSWORD)) { /* 공유기에 접속했는지 여부 검사 */
        Serial.print("Join AP success\r\n");
        Serial.print("IP:");
        Serial.println( wifi.getLocalIP().c_str()); /* 접속했다면 ESP8266이 할당받은 IP 출력 */
    } else {
        Serial.print("Join AP failure\r\n");
    }
    
    if (wifi.disableMUX()) {
        Serial.print("single ok\r\n");
    } else {
        Serial.print("single err\r\n");
    }
    
    Serial.print("setup end\r\n");

    pinMode(LED_BUILTIN, OUTPUT);
}
 
void loop(void)
{
    if(isConnected == false){
      while(1){
        if(wifi.createTCP(HOST_NAME, HOST_PORT)){
          Serial.print("create tcp ok\r\n");
          isConnected = true;
          printUsage();
          break;
        }else{
          Serial.print("create tcp err\r\n");
        }
      }
    }

    uint8_t buffer[128] = {0};
    uint32_t len = wifi.recv(buffer, sizeof(buffer), 10000);
    if(len > 0){
      Serial.print("Received:[");
      for(uint32_t i=0; i<len; i++){
        Serial.print((char)buffer[i]);
      }
      Serial.print("]\r\n");

      char command = buffer[0];
      int ledStatus = digitalRead(LED_BUILTIN);

      switch(command){
        case '1':
        if(ledStatus == LOW){
          digitalWrite(LED_BUILTIN, HIGH);
          sprintf(buffer, "LED is on\n");
          wifi.send(buffer, strlen(buffer));
        }
        else{
          sprintf(buffer, "LED is already on\n");
          wifi.send(buffer, strlen(buffer));
        }
        break;

        case '2':
        if(ledStatus == HIGH){
          digitalWrite(LED_BUILTIN, LOW);
          sprintf(buffer, "LED is off.\n");
          wifi.send(buffer, strlen(buffer));
        }
        else{
          sprintf(buffer, "LED is already off.\n");
          wifi.send(buffer, strlen(buffer));
        }
        break;

        case 'S':
        case 's':
        if(ledStatus == LOW){
          sprintf(buffer, "LED status : off \n");
          wifi.send(buffer, strlen(buffer));
        }
        else{
          sprintf(buffer, "LEF status: on\n");
          wifi.send(buffer, strlen(buffer));
        }
        break;

        default:
        break;
      }
    }
}
     
