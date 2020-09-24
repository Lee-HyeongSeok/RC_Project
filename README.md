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

