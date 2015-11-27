import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class curvas3D01 extends PApplet {




ArrayList <Agent> agentList;
ArrayList <Edge> edgeList;

PeasyCam cam;

int num=8;
int r=200;
float f=2;
boolean sw = true;

// Chasing list
int[] orderList = {4,0,6,2,7,1,5,3};

public void setup(){
  

  // Initializing PeasyCam
  cam = new PeasyCam(this,600);
  cam.setMinimumDistance(50);
  cam.setMaximumDistance(1500);

  frameRate(12);
  //smooth();

  // Initializing Arraylists
  agentList = new ArrayList<Agent>();
  edgeList = new ArrayList<Edge>();

  for(int i=0;i<num;i++){
    float z = 0;

    if (i>3) {
      z = sqrt(2)*r;
    }

    int idChase = orderList[i];
    PVector position=new PVector(r*cos(2*PI*i/num*2),r*sin(2*PI*i/num*2),z);
  
    Agent a=new Agent(i, idChase, position);

    agentList.add(a);
  }
    
}

public void draw(){

  // Drawing background
  background(20);

  for(Agent a:agentList){   
    a.chase();
  }

  for(Agent a:agentList){

    a.update();
    a.render();
    
  }

  for (Edge e : edgeList) {

    e.render();

  }
}
  

public void keyPressed(){

  if (key == 'r'){

    sw = !sw;

    if (sw == false) {
      noLoop();
    }else{

      loop();
    }
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
  
  


class Agent{
  int id, idToChase;
  PVector pos, posOld, Vdist;
  Agent b;
  
  Agent(int _id, int _idToChase, PVector _pos){
    id=_id;
    pos=_pos;
    posOld = pos;
    idToChase = _idToChase;
  }
  
  // Calculate vectors
  public void chase(){

    // Identifying what agent to chase
    //int idToChase=(this.id+1)%num;

    b = agentList.get(idToChase); // Sel. the agent to chase
    
    Vdist=PVector.sub(b.posOld,this.posOld);
      
    //float distance=Vdist.mag();
    //Vdist.mult(f);
    Vdist.normalize();
    Vdist.mult(f);
    
  }
  
  public void update(){

    posOld = new PVector (pos.x,pos.y,pos.z);
    this.pos.add(Vdist);

    // println("posOld: "+posOld);
    // println("pos: "+pos);
    // println("////////////////////////////////////////");

  }
  
  public void render(){
    fill(255);
    noStroke();
    pushMatrix();
    translate(0, 0, posOld.z);
    ellipse(posOld.x,posOld.y,4,4);
    popMatrix();
    stroke(255,50,50);
    
    // line(this.posOld.x,this.posOld.y,b.posOld.x,b.posOld.y);

    Edge e = new Edge(new PVector(posOld.x,posOld.y,posOld.z),new PVector(b.posOld.x,b.posOld.y,b.posOld.z));
    edgeList.add(e);
     
    //Painting Vector
    stroke(220,220,220);
    line(this.posOld.x, this.posOld.y, this.posOld.z, this.posOld.x+Vdist.x, this.posOld.y+Vdist.y, this.posOld.z+Vdist.z);
  }
  
}
class Edge  {

	PVector pos0, pos1;

	Edge (PVector _pos0, PVector _pos1) {
		pos0 = _pos0;
		pos1 = _pos1;		
	}

	public void render(){
		stroke (255);
		line(pos0.x, pos0.y, pos0.z, pos1.x, pos1.y, pos1.z);

	}

}
  public void settings() {  size(600,600,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "curvas3D01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
