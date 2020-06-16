package cse;

import nodes.ASTNode;

public class TauOperator extends ControlOperator {
	private int n;

	public TauOperator(ASTNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	public TauOperator(int n) {
		this.n = n;
		// TODO Auto-generated constructor stub
	}
	
	public int getN() {
		return this.n;
	}

}
