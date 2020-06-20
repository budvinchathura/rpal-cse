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
	private Boolean booleanValue;
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
			
		case true_value:
			this.type = NodeType.true_value;
			this.booleanValue = true;
			break;
		
		case false_value:
			this.type = NodeType.false_value;
			this.booleanValue = false;
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
	
	public ControlOperator(NodeType type,String value) {
		this.type = type;
		if (type == NodeType.string) {
			this.stringValue = value;
		}else if (type == NodeType.identifier) {
			this.name = value;
		}		
	}
	
	public ControlOperator(NodeType type,int value) {
		this.type = type;
		this.integerValue = value;
	}
	
	public ControlOperator(NodeType type) {
		this.type = type;
		switch (type) {
		case true_value:
			this.type = NodeType.true_value;
			this.booleanValue = true;
			break;
		
		case false_value:
			this.type = NodeType.false_value;
			this.booleanValue = false;
			break;

		default:
			break;
		}
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public NodeType getType() {
		return this.type;
	}
	
	public String getStringValue() {
		return this.stringValue;
	}
	
	public int getIntegerValue() {
		return this.integerValue;
	}
	
	public Boolean getBooleanValue() {
		return this.booleanValue;
	}
}
