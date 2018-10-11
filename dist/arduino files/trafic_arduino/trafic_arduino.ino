
#define redA 2
#define yelA 3
#define greenAL 4
#define greenAR 5

#define redB 6
#define yelB 7 
#define greenBL 8
#define greenBR 9


enum Road{AL,AR,BL,BR};
void setup() {
  // put your setup code here, to run once:
Serial.begin(9600);
pinMode(13,OUTPUT);

pinMode(redA,OUTPUT);
pinMode(yelA,OUTPUT);
pinMode(greenAL,OUTPUT);
pinMode(greenAR,OUTPUT);

pinMode(redB,OUTPUT);
pinMode(yelB,OUTPUT);
pinMode(greenBL,OUTPUT);
pinMode(greenBR,OUTPUT);
offAllLight();
  digitalWrite(13,HIGH);
}

void loop() {
//   put your main code here, to run repeatedly:
  String val;
  long rvalue;
  unsigned int a,b,c,d,e;
  while(!Serial.available());
//  if(Serial.available()){
     
     val=Serial.readStringUntil('\n');
      Serial.println(val);
      delay(1000);
//  }

  while(!Serial.available()){
  
//  else{
//    val="6025252525";
//  }
    
    if(val!="stop"){

//    String ct=val.substring(0,val.length()-8);
    
    String ds=val.substring(val.length()-2);
    String cs=val.substring(val.length()-4,val.length()-2);
    String bs=val.substring(val.length()-6,val.length()-4);
    String as=val.substring(val.length()-8,val.length()-6);

    e=100;
    a = as.toInt();
    b = bs.toInt();
    c = cs.toInt();
    d = ds.toInt();
    
    Serial.println(a);
    Serial.println(b);
    Serial.println(c);
    Serial.println(d);
    Serial.println(e);
    
    openRoad(AL);
    test("AL");
    Serial.println(e*a*9);
              delay(e*a*9);
    alertRoad(BL);
    test("AL+BL");
    Serial.println(e*a*1);
              delay(e*a*1);
        
    openRoad(BL);
    test("BL");
    Serial.println(e*b*9);
              delay(e*b*9);
    alertRoad(AR);
    test("BL+AR");
    Serial.println(e*b*1);
              delay(e*b*1);

    openRoad(AR);
    test("AR");
    Serial.println(e*c*9);
              delay(e*c*9);
    alertRoad(BR);
    test("AR+BR");
    Serial.println(e*c*1);
              delay(e*c*1);

    openRoad(BR);
    test("BR");
    Serial.println(e*d*9);
              delay(e*d*9);
    alertRoad(AL);
    test("BR+AL");
    Serial.println(e*d*1);
              delay(e*d*1);
    }else{
      closeAllRoad();
    }

  Serial.println("****************END**********************");
//  delay(10000000);

  }

  
}

void test(String a){
  
  Serial.println(a);
  
  }



void openRoad(Road r){
  closeAllRoad();
  switch(r){
      case AL:
      digitalWrite(redA,HIGH);
      digitalWrite(greenAL,LOW);
      break;
      case AR:
      digitalWrite(redA,HIGH);
      digitalWrite(greenAR,LOW);
      break;
      case BL:
      digitalWrite(redB,HIGH);
      digitalWrite(greenBL,LOW);
      break;
      case BR:
      digitalWrite(redB,HIGH);
      digitalWrite(greenBR,LOW);
      break;
    
    
    
    }
  
  
  }

void closeAllRoad(){
  digitalWrite(redA,LOW);
  digitalWrite(yelA,HIGH);
  digitalWrite(greenAL,HIGH);
  digitalWrite(greenAR,HIGH);

  digitalWrite(redB,LOW);
  digitalWrite(yelB,HIGH);
  digitalWrite(greenBL,HIGH);
  digitalWrite(greenBR,HIGH);
  
  }

void offAllLight(){
  digitalWrite(redA,HIGH);
  digitalWrite(yelA,HIGH);
  digitalWrite(greenAL,HIGH);
  digitalWrite(greenAR,HIGH);

  digitalWrite(redB,HIGH);
  digitalWrite(yelB,HIGH);
  digitalWrite(greenBL,HIGH);
  digitalWrite(greenBR,HIGH);
  
  }

void alertRoad(Road r){
  
switch(r){
  case AL:
  r=AR;
  case AR:
  digitalWrite(redA,HIGH);
  digitalWrite(yelA,LOW);
  digitalWrite(greenAL,HIGH);
  digitalWrite(greenAR,HIGH);
  break;
  case BL:
  r=BR;
  case BR:
  digitalWrite(redB,HIGH);
  digitalWrite(yelB,LOW);
  digitalWrite(greenBL,HIGH);
  digitalWrite(greenBR,HIGH);
  break;
  
  
  
  }
  
  
  }


