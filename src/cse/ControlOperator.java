package cse;

import nodes.ASTNode;
import nodes.IdentifierNode;
import nodes.IntegerNode;
import nodes.NodeType;
import nodes.StringNode;

public class ControlOperator {
	protected NodeType type;
	private String name;
	private String stringValue;
	private int integerValue;
	public ControlOperator(ASTNode node) {
		switch (node.getType()) {
		case identifier:
			this.type = NodeType.identifier;
			this.name = ((IdentifierNode) node).getName();
			break;
			
		case integer:
			this.type = NodeType.integer;
			this.integerValue = ((IntegerNode) node).getValue();
			break;
			
		case string:
			this.type = NodeType.string;
			this.stringValue = ((StringNode) node).getValue();
			break;

		default:
			this.type = node.getType();
			break;
		}
	}
	
	public ControlOperator() {
		this.type = null;
		this.name = null;
		this.stringValue = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStringValue() {
		return this.stringValue;
	}
	
	public int getIntegerValue() {
		return this.integerValue;
	}
}
