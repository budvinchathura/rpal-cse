package cse;

import nodes.ASTNode;
import nodes.NodeType;

public class BetaOperator extends ControlOperator {
	private int trueCSid;	// control structure when condition is true
	private int falseCSid;	// control structure when condition is false

	public BetaOperator(ASTNode node) {
		super(node);
	}

	public BetaOperator(int trueId, int falseId) {
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
