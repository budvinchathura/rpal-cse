package main;

import java.util.ArrayList;

import nodes.ASTNode;
import nodes.ASTNodeFactory;

public class AST {
	private ArrayList<String> rawData;
	private ASTNode root;
	private ASTNodeFactory factory;

	public AST(ArrayList<String> data) {
		this.rawData = data;
		this.factory = new ASTNodeFactory();
		this.root = this.factory.makeNode(data.get(0), data.get(0));
//		this.root.setParent(this.root);
		this.root.setDepth(0);
		this.makeAST();
	}

	private void makeAST() {
		int prevDots = this.getDots(this.rawData.get(0));
		ASTNode prevNode = this.root;
		for (int counter = 1; counter < this.rawData.size(); counter++) {

			int nDots = this.getDots(this.rawData.get(counter));
			ASTNode currentNode = this.factory.makeNode(
					this.rawData.get(counter), this.removeDots(this.rawData.get(counter)));

			// current node is in same level as the previous node
			if (nDots == prevDots) {
				prevNode.getParent().addChild(currentNode);
			} 
			// current node is a child of previous node
			else if (nDots == prevDots + 1) {
				prevNode.addChild(currentNode);
			} 
			// current node is in level of the parent of previous node
			else {
				int diff = prevDots - nDots;
				ASTNode properParent = prevNode.getParent();
				
				// go up in hierarchy
				for (int i = 1; i <= diff; i++) {
					properParent = properParent.getParent();
				}
				properParent.addChild(currentNode);
			}
			prevDots = nDots;
			prevNode = currentNode;
		}

	}

	// returns number of preceding dots in a string
	private int getDots(String s) {
		int n = 0;
		for (char ch : s.toCharArray()) {
			if (ch == '.') {
				n++;
			} else {
				break;
			}
		}
		return n;
	}
	
	private String removeDots(String s) {
		int nDots = this.getDots(s);
		return s.substring(nDots);
	}
	
	// standardize nodes recursively
	public void standardize() throws Exception {
		this.root.standardize();
	}
	
	public void updateDepth() {
		this.root.updateDepth();
	}
	
	
	// for debug purpose
	public void printAST() {
		this.root.printSubtree();
	}
	
	public ASTNode getRoot() {
		return this.root;
	}

}
