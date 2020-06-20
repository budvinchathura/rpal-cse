package nodes;

public class AtNode extends ASTNode {

	public AtNode(String contentData) {
		super(contentData);
		this.type = NodeType.at;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		
		ASTNode e1 = this.children.get(0);
		ASTNode n = this.children.get(1);
		ASTNode e2 = this.children.get(2);
		
		GammaNode gammaNode = new GammaNode();
		
		gammaNode.addChild(n);
		gammaNode.addChild(e1);
		
		this.type = NodeType.gamma;
		
		this.children.clear();
		
		this.addChild(gammaNode);
		this.addChild(e2);		
	
	}

}