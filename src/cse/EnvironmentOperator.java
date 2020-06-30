package cse;

import nodes.ASTNode;
import nodes.NodeType;

// class for e operator in Control and Stack
public class EnvironmentOperator extends ControlOperator {
	private int id;

	public EnvironmentOperator(ASTNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	public EnvironmentOperator(int id) {
		this.type = NodeType.env_operator;
		this.id = id;
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return this.id;
	}

}
