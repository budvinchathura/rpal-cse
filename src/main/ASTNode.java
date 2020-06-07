package main;

import java.util.ArrayList;

public class ASTNode {
	private String content;
	private ASTNode parent;
	private ArrayList<ASTNode> children;
	
	public ASTNode(String contentData) {
		this.content = contentData;
		this.children = new ArrayList<>();
	}
	

	public void printSubtree() {
		System.out.println(this.content);
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
}
