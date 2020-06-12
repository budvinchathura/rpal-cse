package nodes;

import java.util.ArrayList;

public class FunctionFormNode extends ASTNode{

	public FunctionFormNode(String contentData) {
		super(contentData);
		this.type = NodeType.fcn_form;
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		this.type = NodeType.equal;
				
		ASTNode p = this.children.get(0);
		ASTNode e = this.children.get(this.children.size()-1);
		ArrayList<ASTNode> v = new ArrayList<>();
		for (int i = 1; i < this.children.size()-1; i++) {
			v.add(this.children.get(i));
			
		}
		this.children.clear();
		this.addChild(p);
		
		ASTNode lastLambda = this;
		for (ASTNode vChild : v) {
			ASTNode newLambda = new LambdaNode();
			lastLambda.addChild(newLambda);
			newLambda.addChild(vChild);
			lastLambda = newLambda;			
		}
		lastLambda.addChild(e);
	}
	
	

}
