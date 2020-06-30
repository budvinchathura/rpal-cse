package cse;

import java.util.HashMap;

public class Environment {
	private Environment parent;
	private HashMap<String, ControlOperator> variables;		// variables and their values
	private int id;

	public Environment(Environment parent, int id) {
		this.parent = parent;
		this.id = id;
		variables = new HashMap<>();
	}
	
	public void addVariable(String name, ControlOperator v) {
		this.variables.put(name, v);
	}
	
	public ControlOperator search(String name) throws Exception {
		Boolean hasVariable = this.variables.containsKey(name);
		if (hasVariable) {
			return this.variables.get(name);
		}
		
		// when this is a primitive env and still can not find the value for variable
		if (this.parent == null) {
			throw new Exception(name + " is not defined");
		}else {
			return this.parent.search(name);
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public Environment getParent() {
		return this.parent;
	}
	

}
