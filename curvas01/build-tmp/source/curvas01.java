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
int r=350;
float f=10;

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
  rect(0,0,width,height);
  for(Agent a:agentList){
    a.render();
    a.chase();
    
  }
}
  
public void mousePressed() {
  noLoop();
}
  
  


class Agent{
  int id;
  PVector pos, posOld, Vdist;
  PVector vel;
  
  Agent(int _id, PVector _pos, PVector _vel){
    id=_id;
    pos=_pos;
    vel=_vel;
    posOld = pos;
  }
  
  public void chase(){
    // Identifying what agent to chase
    int idToChase=(this.id+1)%num;
    Agent b=agentList.get(idToChase); // Sel. the agent to chase
    println("idToChase_" + this.id + ": " +idToChase);

    if (idToChase == 0) {
      Vdist=PVector.sub(b.posOld,this.pos);
    }else{
      Vdist=PVector.sub(b.posOld,this.pos);
    }
    
    //float distance=Vdist.mag();
    //Vdist.mult(f);
    Vdist.normalize();
    Vdist.mult(f);
    
    // if(distance<10 || distance > 300){
    //   //Vdist.mult(-1);
    // }
    this.posOld = this.pos;
    this.pos.add(Vdist);
    stroke(255,50,50);
    line(this.pos.x,this.pos.y,b.posOld.x,b.posOld.y);

    //Painting V
    stroke(220,220,220);
    line(this.pos.x, this.pos.y, this.pos.x+Vdist.x, this.pos.y+Vdist.y);
  }
  
  
  
  public void render(){
    fill(255);
    noStroke();
    ellipse(pos.x,pos.y,4,4);
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
