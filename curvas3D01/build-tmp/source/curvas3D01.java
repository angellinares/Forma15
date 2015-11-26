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

public class curvas3D01 extends PApplet {


PFont font1;
ArrayList <Agent> agentList;
int num=8;
int r=200;
float f=0.1f;
boolean sw = true;

public void setup(){
  
  camera(280.0f, 100.0f, 680.0f, width/2, height/2, 0.0f,0.0f, 0.0f, 1.0f);
  
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

public void draw(){
  fill(0,20);
  noStroke();
  //rect(0,0,width,height);
  for(Agent a:agentList){
    a.chase();
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
  int id;
  PVector pos;
  PVector vel;
  
  Agent(int _id, PVector _pos, PVector _vel){
    id=_id;
    pos=_pos;
    vel=_vel;
  }
  
  public void chaseOld(){
    int idToChase=(this.id+1)%num;
    Agent b=agentList.get(idToChase);
    PVector Vdist=PVector.sub(b.pos,this.pos);
    float distance=Vdist.mag();
    Vdist.mult(f);
    
    if(distance<10 || distance > 300){
      //Vdist.mult(-1);
    }
    this.pos.add(Vdist);
    stroke(255,50,50);
    line(this.pos.x,this.pos.y,b.pos.x,b.pos.y);
  }
  
  public void chase(){
    int idToChase=(this.id+1)%num;
    Agent b=agentList.get(idToChase);
    PVector Vdist=PVector.sub(b.pos,this.pos);
    float distance=Vdist.mag();
    Vdist.mult(f);
 
    this.pos.add(Vdist);
    stroke(255,50,50);
    line(this.pos.x,this.pos.y,this.pos.z, b.pos.x,b.pos.y, b.pos.z);
  }
  
  
  
  public void render(){
    fill(255);
    noStroke();
    pushMatrix();
    translate(0,0,pos.z);
    ellipse(pos.x,pos.y,4,4);
   
    textFont(font1);
    text(id,pos.x,pos.y);
     popMatrix();
  }
  
}
  public void settings() {  size(600,600,P3D);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "curvas3D01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
