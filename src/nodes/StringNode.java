package nodes;

public class StringNode extends ASTNode {
	private String value;
	public StringNode(String contentData, String value) {
		super(contentData);
		this.type = NodeType.string;
		this.value = value;
	}
	
	@Override
	public void printSubtree() {
		this.printDepth();
		System.out.print(this.type+ " : "+this.value);
		System.out.println();
		for (ASTNode child : this.children) {
			child.printSubtree();
	         		
	      }
	}
	
	public String getValue() {
		return this.value;
	}

}
