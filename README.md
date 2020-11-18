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


