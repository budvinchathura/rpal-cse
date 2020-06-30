package nodes;

import java.util.ArrayList;

public class LambdaNode extends ASTNode {

	public LambdaNode(String contentData) {
		super(contentData);
		this.type = NodeType.lambda;
	}
	
	public LambdaNode() {
		super();
		this.type = NodeType.lambda;
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		if (this.children.size() == 2) {
			return;
		}
		ASTNode e = this.children.get(this.children.size()-1);
		ASTNode v1 = this.children.get(0);
		ArrayList<ASTNode> v = new ArrayList<>();
		for (int i = 1; i < this.children.size()-1; i++) {
			v.add(this.children.get(i));
			
		}
		this.children.clear();
		
		ASTNode lastLambda = this;
		lastLambda.addChild(v1);
		
		for (ASTNode vChild : v) {
			ASTNode newLambda = new LambdaNode();
			newLambda.addChild(vChild);
			lastLambda = newLambda;			
		}
		lastLambda.addChild(e);
	}

}
