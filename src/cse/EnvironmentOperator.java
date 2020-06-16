package cse;

import nodes.ASTNode;

public class EnvironmentOperator extends ControlOperator {
	private int id;

	public EnvironmentOperator(ASTNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	public EnvironmentOperator(int id) {
		this.id = id;
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return this.id;
	}

}
