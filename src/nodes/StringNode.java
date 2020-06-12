package nodes;

public class StringNode extends ASTNode {
	private String value;
	public StringNode(String contentData, String value) {
		super(contentData);
		this.type = NodeType.string;
		this.value = value;
		// TODO Auto-generated constructor stub
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

}
