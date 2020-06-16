package cse;

import java.util.HashMap;

public class ControlStrcutureStore {
	private int currentId;
	private HashMap<Integer, ControlStructure> controlStructures;

	public ControlStrcutureStore() {
		this.currentId  =-1;
		this.controlStructures = new HashMap<>();
		// TODO Auto-generated constructor stub
	}
	
	public int getNextId() {
		this.currentId+=1;
		return this.currentId;
		
	}
	
	public int getCurrentId() {
		return this.currentId;
	}
	
	public int addControlStructure(ControlStructure cs) {
		this.controlStructures.put(this.getNextId(), cs);
		return this.currentId;
	}
	
	public ControlStructure getControlStructure(int id) {
		return this.controlStructures.get(id);
	}

}
