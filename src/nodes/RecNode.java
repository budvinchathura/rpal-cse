package nodes;

public class RecNode extends ASTNode {

	public RecNode(String contentData) {
		super(contentData);
		this.type = NodeType.rec;
	}

	@Override
	public void standardize() throws Exception {
		super.standardize();
		if(this.children.get(0).getType() != NodeType.equal) {
			throw new Exception("Rec node should have = node as the child");
		}
		this.type = NodeType.equal;
				
		
		ASTNode x = this.children.get(0).getChildren().get(0);
		ASTNode e = this.children.get(0).getChildren().get(1);
		ASTNode lambdaNode = new LambdaNode();
		ASTNode gammaNode = new GammaNode();
		ASTNode yStarNode = new ASTNode(NodeType.y_star);
		
		gammaNode.addChild(yStarNode);
		gammaNode.addChild(lambdaNode);
		lambdaNode.addChild(x);
		lambdaNode.addChild(e);

		
		this.children.clear();
		this.addChild(x);
		this.addChild(gammaNode);
		
	}
}
