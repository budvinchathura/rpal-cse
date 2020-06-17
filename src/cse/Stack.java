package cse;

import java.util.ArrayList;

public class Stack {
	private ArrayList<ControlOperator> stack;
	public Stack() {
		stack = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public void add(ControlOperator operator) {
		this.stack.add(0,operator);
	}
	
	public ArrayList<ControlOperator> getStack(){
		return this.stack;
	}
	
	public int getLength() {
		return this.stack.size();
	}
	
	public ControlOperator peek() {
		return this.stack.get(0);
	}
	
	public ControlOperator pop() {
		return this.stack.remove(0);
	}

}
