int sensor = 0;
void setup() {  
  Serial.begin(9600);
}
void loop() {
  int FNDval = 0;
  sensor = analogRead(A0);  //토양수분센서값 읽기
  Serial.print(sensor);
  Serial.print("  ");
  FNDval = map(sensor,0,1023,9,0);
  Serial.println(FNDval);
  delay(1000);
}
