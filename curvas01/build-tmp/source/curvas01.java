import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class curvas01 extends PApplet {


ArrayList <Agent> agentList;
int num=4;
int r=30;
float f=10;
boolean sw = true;

public void setup(){
  
  
  frameRate(12);
  
  agentList=new ArrayList<Agent>();
  
  for(int i=0;i<num;i++){
    
    PVector position=new PVector(width/2+r*cos(2*PI*i/num),height/2+r*sin(2*PI*i/num),0);
    PVector velocity=new PVector(0,0,0);
    Agent a=new Agent(i,position, velocity);
    agentList.add(a);
  }
    
}

public void draw(){
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
  
public void mousePressed() {
  
  sw = !sw;

  if (sw == false) {
    noLoop();
  }else{
    loop();
  }
}
  
  


class Agent{
  int id, idToChase;
  PVector pos, posOld, Vdist,vel;
  Agent b;
  
  Agent(int _id, PVector _pos, PVector _vel){
    id=_id;
    pos=_pos;
    vel=_vel;
    posOld = pos;
  }
  
  // Calculate vectors
  public void chase(){

    // Identifying what agent to chase
    int idToChase=(this.id+1)%num;
    b = agentList.get(idToChase); // Sel. the agent to chase
    
    Vdist=PVector.sub(b.posOld,this.posOld);
      
    //float distance=Vdist.mag();
    //Vdist.mult(f);
    Vdist.normalize();
    Vdist.mult(-f);
    
  }
  
  public void update(){

    posOld = new PVector (pos.x,pos.y,pos.z);
    this.pos.add(Vdist);

    println("posOld: "+posOld);
    println("pos: "+pos);
    println("////////////////////////////////////////");

  }
  
  public void render(){
    fill(255);
    noStroke();
    ellipse(posOld.x,posOld.y,4,4);
    stroke(255,50,50);
    
    line(this.posOld.x,this.posOld.y,b.posOld.x,b.posOld.y);
    
     
    //Painting Vector
    stroke(220,220,220);
    line(this.posOld.x, this.posOld.y, this.posOld.x+Vdist.x, this.posOld.y+Vdist.y);
  }
  
}
  public void settings() {  size(900,900);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "curvas01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
