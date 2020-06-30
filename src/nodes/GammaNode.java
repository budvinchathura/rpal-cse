package nodes;

public class GammaNode extends ASTNode {

	public GammaNode(String contentData) {
		super(contentData);
		this.type = NodeType.gamma;
	}
	public GammaNode() {
		super();
		this.type = NodeType.gamma;
	}

}
