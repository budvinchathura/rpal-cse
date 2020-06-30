package cse;

import java.util.ArrayList;

public class Control {
	private ArrayList<ControlOperator> stack;
	public Control() {
		stack = new ArrayList<>();
	}
	
	// load all elements of a given control structure
	public void loadCS(ControlStructure cs) {
		for (ControlOperator controlOperator : cs.getSequence()) {
			this.stack.add(controlOperator);
		}
	}
	
	// returns the element at the end
	public ControlOperator peek() {
		return this.stack.get(stack.size()-1);
	}
	
	// removes and returns the element at the end
	public ControlOperator pop() {
		return this.stack.remove(stack.size()-1);
	}
	
	// add new element
	public void add(ControlOperator operator) {
		this.stack.add(operator);
	}
	
	
	
	public ArrayList<ControlOperator> getStack(){
		return this.stack;
	}
	
	public int getLength() {
		return this.stack.size();
	}


}
