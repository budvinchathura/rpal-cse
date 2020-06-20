package nodes;

import java.util.ArrayList;

public class ASTNode {
	private String rawString;
	protected int depth;
	protected NodeType type;
	protected ASTNode parent;
	protected boolean standard;
	protected ArrayList<ASTNode> children;

	public ASTNode(String rawString) {
		this.rawString = rawString;
		this.children = new ArrayList<>();
		this.type = null;
		this.standard = false;
		this.depth = 0;
	}
	
	public ASTNode(String rawString, NodeType type) {
		this.rawString = rawString;
		this.children = new ArrayList<>();
		this.type = type;
		this.standard = false;
		this.depth = 0;
	}

	public ASTNode() {
		this.rawString = "derived node";
		this.children = new ArrayList<>();
		this.type = null;
		this.standard = false;
		this.depth = 0;
	}
	
	public ASTNode(NodeType type) {
		this.rawString = "derived node";
		this.children = new ArrayList<>();
		this.type = type;
		this.standard = false;
		this.depth = 0;
	}

	public void printSubtree() {
		this.printDepth();
		System.out.print(this.type);
		System.out.println();
		for (ASTNode child : this.children) {
			child.printSubtree();

		}
	}

	protected void printDepth() {
		for (int i = 0; i <this.depth; i++) {
			System.out.print(".");
		}
		
	}

	public void standardize() throws Exception {
		this.standardizeChildren();
		this.standard = true;
	}

	private void standardizeChildren() throws Exception {
		for (ASTNode child : this.children) {
			child.standardize();
		}
	}

	public void setParent(ASTNode p) {
		this.parent = p;
	}

	public ASTNode getParent() {
		return this.parent;
	}
	
	public ArrayList<ASTNode> getChildren(){
		return this.children;
	}

	public void addChild(ASTNode c) {
		this.children.add(c);
		c.setDepth(this.depth + 1);
		c.setParent(this);
	}

	public NodeType getType() {
		return this.type;
	}

	public void setDepth(int n) {
		this.depth = n;
	}
	
	public void updateDepth() {
		if (this.depth >0) {
			this.depth = this.parent.getDepth() + 1;
		}
		
		for (ASTNode child : this.children) {
			child.updateDepth();
		}
	}

	public int getDepth() {
		return this.depth;
	}
}
