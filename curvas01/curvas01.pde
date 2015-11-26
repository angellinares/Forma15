
ArrayList <Agent> agentList;
int num=4;
int r=30;
float f=10;
boolean sw = true;

void setup(){
  size(900,900);
  smooth();
  frameRate(12);
  
  agentList=new ArrayList<Agent>();
  
  for(int i=0;i<num;i++){
    
    PVector position=new PVector(width/2+r*cos(2*PI*i/num),height/2+r*sin(2*PI*i/num),0);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(i,position, velocity);
    agentList.add(a);
  }
    
}

void draw(){
  fill(0,5);
  noStroke();
  //rect(0,0,width,height);
  for(Agent a:agentList){   
    a.chase();   
  }

  for(Agent a:agentList){

    a.update();
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
  
  


