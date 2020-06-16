package cse;

import java.util.ArrayList;

public class Control {
	private ArrayList<ControlOperator> stack;
	public Control() {
		stack = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public void loadCS(ControlStructure cs) {
		for (ControlOperator controlOperator : cs.getSequence()) {
			this.stack.add(controlOperator);
		}
	}
	
	public ControlOperator peek() {
		return this.stack.get(stack.size()-1);
	}
	
	public ControlOperator pop() {
		return this.stack.remove(stack.size()-1);
	}
	
	public void add(ControlOperator operator) {
		this.stack.add(operator);
	}
	
	
	
	public ArrayList<ControlOperator> getStack(){
		return this.stack;
	}

}
