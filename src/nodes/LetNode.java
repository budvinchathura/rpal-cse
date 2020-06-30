package nodes;


public class LetNode extends ASTNode{

	public LetNode(String contentData) {
		super(contentData);
		this.type = NodeType.let;
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		if(this.children.get(0).getType() != NodeType.equal) {
			throw new Exception("Let node should have = node as first child");
		}
		this.type = NodeType.gamma;
				
		ASTNode p = this.children.get(1);
		
		ASTNode x = this.children.get(0).getChildren().get(0);
		ASTNode e = this.children.get(0).getChildren().get(1);
		ASTNode lambdaNode = new LambdaNode();
		lambdaNode.addChild(x);
		lambdaNode.addChild(p);
		
		this.children.clear();
		this.addChild(lambdaNode);
		this.addChild(e);
		
	}

}
