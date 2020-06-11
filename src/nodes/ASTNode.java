package nodes;

import java.util.ArrayList;

public class ASTNode {
	private String content;
	protected NodeType type;
	protected ASTNode parent;
	protected ArrayList<ASTNode> children;

	
	public ASTNode(String contentData) {
		this.content = contentData;
		this.children = new ArrayList<>();
		this.type = null;
	}
	

	public void printSubtree() {
		System.out.println(this.type);
		for (ASTNode child : this.children) {
			child.printSubtree();
	         		
	      }
	}
	
	public void setParent(ASTNode p) {
		this.parent = p;
	}
	
	public ASTNode getParent() {
		return this.parent;
	}
	
	public void addChild(ASTNode c) {
		this.children.add(c);
	}
	
	public NodeType getType() {
		return this.type;
	}
}
