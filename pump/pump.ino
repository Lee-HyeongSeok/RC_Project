int A_1A = 11;
int A_2A = 12;

void setup() {
  pinMode(A_1A, OUTPUT);
  pinMode(A_2A, OUTPUT);
}

void loop() {
  char Android_Button;
 
 pump(1);

  /*
  if(Android_Button == 'P'){
    pump(1);
  }else{
    stop();
  }
  */
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
  digitalWrite(A_1A, LOW);
  digitalWrite(A_2A, LOW);
}
