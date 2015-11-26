class Agent{
  int id;
  PVector pos;
  PVector vel;
  
  Agent(int _id, PVector _pos, PVector _vel){
    id=_id;
    pos=_pos;
    vel=_vel;
  }
  
  void chase(){
    int idToChase=(this.id+1)%num;
    Agent b=agentList.get(idToChase);
    PVector Vdist=PVector.sub(b.pos,this.pos);
    float distance=Vdist.mag();
    Vdist.mult(-f);
    
    if(distance<10 || distance > 300){
      //Vdist.mult(-1);
    }
    this.pos.add(Vdist);
    stroke(255,50,50);
    line(this.pos.x,this.pos.y,b.pos.x,b.pos.y);
  }
  
  
  
  void render(){
    fill(255);
    noStroke();
    ellipse(pos.x,pos.y,4,4);
  }
  
}
