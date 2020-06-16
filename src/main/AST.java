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

			if (nDots == prevDots) {
				prevNode.getParent().addChild(currentNode);
//				currentNode.setParent(prevNode.getParent());
			} else if (nDots == prevDots + 1) {
				prevNode.addChild(currentNode);
//				currentNode.setParent(prevNode);
			} else {
				int diff = prevDots - nDots;
				ASTNode properParent = prevNode.getParent();
				for (int i = 1; i <= diff; i++) {
					properParent = properParent.getParent();
				}
				properParent.addChild(currentNode);
//				currentNode.setParent(properParent);
			}
			prevDots = nDots;
			prevNode = currentNode;
		}

	}

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
	
	public void standardize() throws Exception {
		this.root.standardize();
	}
	
	public void updateDepth() {
		this.root.updateDepth();
	}
	
	
	
	public void printAST() {
		this.root.printSubtree();
	}
	
	public ASTNode getRoot() {
		return this.root;
	}

}
