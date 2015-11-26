
ArrayList <Agent> agentList;
int num=5;
int r=30;
float f=0.1;

void setup(){
  size(600,600);
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
  rect(0,0,width,height);
  for(Agent a:agentList){
    a.chase();
    a.render();
  }
  
  
}
