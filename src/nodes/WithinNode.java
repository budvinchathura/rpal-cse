package nodes;

public class WithinNode extends ASTNode {

	public WithinNode(String contentData) {
		super(contentData);
		this.type = NodeType.within;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		this.type = NodeType.equal;
		
		ASTNode child1 = this.children.get(0);
		ASTNode child2 = this.children.get(1);
		
		if (!(child1.getType()==NodeType.equal && child2.getType()==NodeType.equal)) {
			throw new Exception("Within node should have = nodes as two children");
		}
		
		ASTNode x1 = child1.getChildren().get(0);
		ASTNode e1 = child1.getChildren().get(1);
		ASTNode x2 = child2.getChildren().get(0);
		ASTNode e2 = child2.getChildren().get(1);
		
		ASTNode gammaNode = new GammaNode();
		ASTNode lambdaNode = new LambdaNode();
		
		lambdaNode.addChild(x1);
		lambdaNode.addChild(e2);
		
		gammaNode.addChild(lambdaNode);
		gammaNode.addChild(e1);
		
		this.children.clear();
		
		this.addChild(x2);
		this.addChild(gammaNode);
		
	}

}
