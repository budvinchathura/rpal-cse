package cse;

import java.util.ArrayList;

import nodes.ASTNode;
import nodes.NodeType;

public class TupleOperator extends ControlOperator {
	private ArrayList<ControlOperator> tuple;

	public TupleOperator(ASTNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

	public TupleOperator(ArrayList<ControlOperator> tuple) {
		this.tuple = tuple;
		this.type = NodeType.tuple;
		// TODO Auto-generated constructor stub
	}
	public ArrayList<ControlOperator> getTuple(){
		return this.tuple;
	}
	

}
