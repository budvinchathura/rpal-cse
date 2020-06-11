package nodes;

public class FunctionFormNode extends ASTNode{

	public FunctionFormNode(String contentData) {
		super(contentData);
		this.type = NodeType.fcn_form;
	}

}
