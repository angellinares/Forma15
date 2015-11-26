
import peasy.*;

PeasyCam cam;

PFont font1;
ArrayList <Agent> agentList;
int num=8;
int r=200;
float f=0.1;
boolean sw = true;

void setup(){
  size(600,600,P3D);

  smooth();
  frameRate(4);
  font1=createFont("Arial",12);
  agentList=new ArrayList<Agent>();

  // Adding peasyCam
  cam = new PeasyCam(this, 600);
  cam.setMinimumDistance(1);
  cam.setMaximumDistance(2000);

  
  for(int i=0;i<num/2;i++){
    PVector position=new PVector(r*cos(2*PI*(i)/num*2),r*sin(2*PI*(i)/num*2),0);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(i,position, velocity);
    agentList.add(a);
  }
  
  for(int i=num-1;i>=num/2;i--){
    PVector position=new PVector(r*cos(2*PI*i/num*2),r*sin(2*PI*i/num*2),200);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(num/2+num-i-1,position, velocity);
    agentList.add(a);
  }
    
}

void draw(){
  
  noStroke();
  background(220);
  for(Agent a:agentList){
    a.chase();
    a.render();
  }
  
  
}

// void mousePressed() {
  
//   sw = !sw;

//   if (sw == false) {
//     noLoop();
//   }else{
//     loop();
//   }
// }
