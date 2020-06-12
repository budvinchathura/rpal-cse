package nodes;

public class WhereNode extends ASTNode {

	public WhereNode(String contentData) {
		super(contentData);
		this.type = NodeType.where;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		if(this.children.get(1).getType() != NodeType.equal) {
			throw new Exception("Where node should have = node as second child");
		}
		this.type = NodeType.gamma;
				
		ASTNode p = this.children.get(0);
		
		ASTNode x = this.children.get(1).getChildren().get(0);
		ASTNode e = this.children.get(1).getChildren().get(1);
		ASTNode lambdaNode = new LambdaNode();
		lambdaNode.addChild(x);
		lambdaNode.addChild(p);
		
		this.children.clear();
		this.addChild(lambdaNode);
		this.addChild(e);
		
	}

}
