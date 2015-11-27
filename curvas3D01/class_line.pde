class Edge  {

	PVector pos0, pos1;

	Edge (PVector _pos0, PVector _pos1) {
		pos0 = _pos0;
		pos1 = _pos1;		
	}

	void render(){
		stroke (255);
		line(pos0.x, pos0.y, pos0.z, pos1.x, pos1.y, pos1.z);

	}

}