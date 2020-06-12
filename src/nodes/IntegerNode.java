package nodes;

public class IntegerNode extends ASTNode {
	private int value;

	public IntegerNode(String contentData, String rawValue) {
		super(contentData);
		this.type = NodeType.integer;
		this.value = Integer.parseInt(rawValue);
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
