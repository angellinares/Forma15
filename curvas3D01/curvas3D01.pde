
PFont font1;
ArrayList <Agent> agentList;
int num=8;
int r=200;
float f=0.1;
boolean sw = true;

void setup(){
  size(600,600,P3D);
  camera(280.0, 100.0, 680.0, width/2, height/2, 0.0,0.0, 0.0, 1.0);
  smooth();
  frameRate(4);
  font1=createFont("Arial",24);
  agentList=new ArrayList<Agent>();
  
  for(int i=0;i<num;i++){
    PVector position=new PVector(width/2+r*cos(2*PI*i/num),height/2+r*sin(2*PI*i/num),0);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(i,position, velocity);
    //agentList.add(a);
  }
  
  for(int i=0;i<num/2;i++){
    PVector position=new PVector(width/2+r*cos(2*PI*(i)/num*2),width/2+r*sin(2*PI*(i)/num*2),0);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(i,position, velocity);
    agentList.add(a);
  }
  
  for(int i=num-1;i>=num/2;i--){
    PVector position=new PVector(width/2+r*cos(2*PI*i/num*2),width/2+r*sin(2*PI*i/num*2),200);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(num/2+num-i-1,position, velocity);
    agentList.add(a);
  }
    
}

void draw(){
  fill(0,20);
  noStroke();
  //rect(0,0,width,height);
  for(Agent a:agentList){
    a.chase();
    a.render();
  }
  
  
}

void mousePressed() {
  
  sw = !sw;

  if (sw == false) {
    noLoop();
  }else{
    loop();
  }
}
