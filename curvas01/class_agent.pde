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
  
  void chase(){
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
  
  
  
  void render(){
    fill(255);
    noStroke();
    ellipse(pos.x,pos.y,4,4);
  }
  
}
