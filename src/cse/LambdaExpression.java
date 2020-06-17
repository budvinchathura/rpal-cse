package cse;

import java.util.ArrayList;

import nodes.IdentifierNode;
import nodes.NodeType;

public class LambdaExpression extends ControlOperator{

	private int id;
	private ArrayList<String> boundedVariables;
	private int envId;
	public LambdaExpression(int id, ArrayList<IdentifierNode> nodes) {
		this.boundedVariables = new ArrayList<>();
		this.id = id;
		this.type = NodeType.lambda_expression;
		for (IdentifierNode idNode : nodes) {
			this.boundedVariables.add(idNode.getName());
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getEnvId() {
		return this.envId;
	}
	
	public void setEnvId(int id) {
		this.envId = id;
	}
	
	public ArrayList<String> getVariables(){
		return this.boundedVariables;
	}

}
