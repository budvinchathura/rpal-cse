package nodes;

public class IdentifierNode extends ASTNode {
	private String name;
	public IdentifierNode(String contentData, String name) {
		super(contentData);
		this.type = NodeType.identifier;
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void printSubtree() {
		this.printDepth();
		System.out.print(this.type+ " : "+this.name);
		System.out.println();
		for (ASTNode child : this.children) {
			child.printSubtree();
	         		
	      }
	}

}
