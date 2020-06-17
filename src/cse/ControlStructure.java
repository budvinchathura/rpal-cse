package cse;

import java.util.ArrayList;

import nodes.ASTNode;
import nodes.IdentifierNode;
import nodes.NodeType;

public class ControlStructure{
	private ArrayList<ControlOperator> operationsSequence;
	private int id;

	public ControlStructure(ASTNode rootNode, ControlStrcutureStore store) throws Exception {
		// TODO Auto-generated constructor stub
		this.operationsSequence = new ArrayList<>();
		this.id = store.addControlStructure(this);
		this.makeSequence(rootNode, store);
		
		
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public ArrayList<ControlOperator> getSequence(){
		return this.operationsSequence;
	}
	
	private void makeSequence(ASTNode rootNode, ControlStrcutureStore store) throws Exception {
		ASTNode currentNode = rootNode;
		if (currentNode == null) {
			return;
		}
		if (currentNode.getType() == NodeType.lambda) {
			
			this.makeLambdaExpression(currentNode, store);
			new ControlStructure(currentNode.getChildren().get(1),store);
		}
		else if(currentNode.getType() == NodeType.cond) {
			this.makeConditional(currentNode, store);
		}
		else if(currentNode.getType() == NodeType.tau) {
			this.makeTau(currentNode, store);
		}
		
		else {
			operationsSequence.add(new ControlOperator(currentNode));
			for (ASTNode child : currentNode.getChildren()) {
				makeSequence(child, store);
			}
		}
	}
	
	private void makeLambdaExpression(ASTNode currentNode, ControlStrcutureStore store) throws Exception {
		ASTNode leftChild = currentNode.getChildren().get(0);
		if (leftChild.getType() == NodeType.identifier) {
			ArrayList<IdentifierNode> nodes = new ArrayList<>();
			nodes.add((IdentifierNode)leftChild);
			operationsSequence.add(new LambdaExpression(store.getCurrentId()+1, nodes));
			
		}else if (leftChild.getType() == NodeType.comma) {
			ArrayList<IdentifierNode> nodes = new ArrayList<>();
			for (ASTNode node : leftChild.getChildren()) {
				if (node.getType() == NodeType.identifier) {
					nodes.add((IdentifierNode) node);
				}else {
					throw new Exception("Children of , node should be identifier nodes.");
				}
			}
			operationsSequence.add(new LambdaExpression(store.getCurrentId()+1, nodes));
		}else {
			throw new Exception("Left child of lambda should be , or identifier");
		}
	}
	
	private void makeConditional(ASTNode currentNode, ControlStrcutureStore store) throws Exception {
		if (currentNode.getChildren().size()!=3) {
			throw new Exception("-> node should contain 3 children");
		}
		
		ControlStructure trueCS = new ControlStructure(currentNode.getChildren().get(1), store);
		ControlStructure falseCS = new ControlStructure(currentNode.getChildren().get(2), store);
		BetaOperator betaOp = new BetaOperator(trueCS.getId(), falseCS.getId());
		this.operationsSequence.add(betaOp);
		this.makeSequence(currentNode.getChildren().get(0), store);
		
	}
	
	private void makeTau(ASTNode currentNode, ControlStrcutureStore store) throws Exception{
		int children_n = currentNode.getChildren().size();
		if (children_n==0) {
			throw new Exception("tau node should have at least one child");
		}
		
		operationsSequence.add(new TauOperator(children_n));
		for (ASTNode child : currentNode.getChildren()) {
			makeSequence(child, store);
		}
	}

}