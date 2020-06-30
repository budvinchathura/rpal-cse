package nodes;

public class TauNode extends ASTNode {

	public TauNode(String contentData) {
		super(contentData);
		this.type = NodeType.tau;
	}
	
	public TauNode() {
		super();
		this.type = NodeType.tau;
	}

}
