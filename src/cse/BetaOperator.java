package cse;

import nodes.ASTNode;
import nodes.NodeType;

public class BetaOperator extends ControlOperator {
	private int trueCSid;
	private int falseCSid;

	public BetaOperator(ASTNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	public BetaOperator(int trueId, int falseId) {
		// TODO Auto-generated constructor stub
		super();
		this.type = NodeType.beta_operator;
		this.trueCSid = trueId;
		this.falseCSid = falseId;
	}
	
	public int getCSid(Boolean condition) {
		if (condition) {
			return this.trueCSid;
		} else {
			return this.falseCSid;
		}
	}

}
