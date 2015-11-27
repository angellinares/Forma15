
import peasy.*;

ArrayList <Agent> agentList;
ArrayList <Edge> edgeList;

PeasyCam cam;

int num=8;
int r=200;
float f=2;
boolean sw = true;

// Chasing list
int[] orderList = {4,0,6,2,7,1,5,3};

void setup(){
  size(600,600,P3D);

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

void draw(){

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
  

void keyPressed(){

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
  
  


