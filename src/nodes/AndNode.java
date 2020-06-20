package nodes;

import java.util.ArrayList;

public class AndNode extends ASTNode {

	public AndNode(String contentData) {
		super(contentData);
		this.type = NodeType.and;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void standardize() throws Exception {
		super.standardize();
		this.type = NodeType.equal;
				
		

		ASTNode commaNode = new ASTNode(NodeType.comma);
		ASTNode tauNode = new TauNode();
		for (int i = 0; i < this.children.size(); i++) {
			if(this.children.get(i).getType()!=NodeType.equal) {
				throw new Exception("And node should only have = nodes as children");
			}
			commaNode.addChild(this.children.get(i).getChildren().get(0));
			tauNode.addChild(this.children.get(i).getChildren().get(1));

		}
		this.children.clear();
		this.addChild(commaNode);
		this.addChild(tauNode);

	}

}
