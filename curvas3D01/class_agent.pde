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
  void chase(){

    // Identifying what agent to chase
    //int idToChase=(this.id+1)%num;

    b = agentList.get(idToChase); // Sel. the agent to chase
    
    Vdist=PVector.sub(b.posOld,this.posOld);
      
    //float distance=Vdist.mag();
    //Vdist.mult(f);
    Vdist.normalize();
    Vdist.mult(f);
    
  }
  
  void update(){

    posOld = new PVector (pos.x,pos.y,pos.z);
    this.pos.add(Vdist);

    // println("posOld: "+posOld);
    // println("pos: "+pos);
    // println("////////////////////////////////////////");

  }
  
  void render(){
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
