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
  void chase(){

    // Identifying what agent to chase
    int idToChase=(this.id+1)%num;
    b = agentList.get(idToChase); // Sel. the agent to chase
    
    Vdist=PVector.sub(b.posOld,this.posOld);
      
    //float distance=Vdist.mag();
    //Vdist.mult(f);
    Vdist.normalize();
    Vdist.mult(-f);
    
  }
  
  void update(){

    posOld = new PVector (pos.x,pos.y,pos.z);
    this.pos.add(Vdist);

    println("posOld: "+posOld);
    println("pos: "+pos);
    println("////////////////////////////////////////");

  }
  
  void render(){
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
