class Agent{
  int id;
  PVector pos;
  PVector vel;
  
  Agent(int _id, PVector _pos, PVector _vel){
    id=_id;
    pos=_pos;
    vel=_vel;
  }
  
  void chaseOld(){
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
  
  void chase(){
    int idToChase=(this.id+1)%num;
    Agent b=agentList.get(idToChase);
    PVector Vdist=PVector.sub(b.pos,this.pos);
    float distance=Vdist.mag();
    Vdist.mult(f);
 
    this.pos.add(Vdist);
    stroke(255,50,50);
    line(this.pos.x,this.pos.y,this.pos.z, b.pos.x,b.pos.y, b.pos.z);
  }
  
  
  
  void render(){
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
