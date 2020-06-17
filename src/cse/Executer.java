package cse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nodes.DummyNode;
import nodes.NodeType;

public class Executer {
	private Control control;
	private Stack stack;
	private ControlStrcutureStore csStore;
	private int nextEnvId;
	private Environment currentEnv;
	// TODO: check for other built-in functions
	private String[] _builtInFunctions = new String[]{"Print"};
	private List<String> builtInFunctions = Arrays.asList(_builtInFunctions);

	public Executer(ControlStrcutureStore csStore) {
		this.control = new Control();
		this.stack = new Stack();
		this.csStore = csStore;
		this.nextEnvId = 0;
		EnvironmentOperator envOp = new EnvironmentOperator(nextEnvId);
		this.currentEnv = new Environment(null, nextEnvId);
		this.nextEnvId+=1;
		control.add(envOp);
		stack.add(envOp);
		control.loadCS(this.csStore.getControlStructure(0));
	}
	
	public void execute() throws Exception {
		while (this.control.getLength()>0 && this.stack.getLength()>0) {
			this.process();
		}
	}
	
	private void process() throws Exception {
		if (control.peek().getType() == NodeType.identifier) {
			ControlOperator op = control.pop();
			ControlOperator value;
			
			if (! builtInFunctions.contains(op.getName())) {
				value = currentEnv.search(op.getName());
			}else {
				value = op;
			}
			stack.add(value);
			return;
		}
		if (control.peek().getType() == NodeType.lambda_expression) {
			LambdaExpression lambdaExp = (LambdaExpression) control.pop();
			lambdaExp.setEnvId(currentEnv.getId());
			stack.add(lambdaExp);
			return;			
		}
		if (control.peek().getType() == NodeType.gamma &&
				stack.peek().getType() == NodeType.lambda_expression) {
			control.pop();
			LambdaExpression lambdaExp = (LambdaExpression) stack.pop();
			int expId = lambdaExp.getId();
			
			
			EnvironmentOperator envOp = new EnvironmentOperator(nextEnvId);
			this.currentEnv = new Environment(this.currentEnv, nextEnvId);
			this.nextEnvId+=1;
			
			
			if (lambdaExp.getVariables().size()>1) {
				// TODO: check for tuple operator
				ArrayList<ControlOperator> variableValues = ((TupleOperator)stack.pop()).getTuple();
				ArrayList<String> variableNames = lambdaExp.getVariables();
				for (int i = 0; i < variableNames.size(); i++) {
					this.currentEnv.addVariable(variableNames.get(i), variableValues.get(i));
				}
				
			} else if (lambdaExp.getVariables().size() ==1){
				this.currentEnv.addVariable(lambdaExp.getVariables().get(0), stack.pop());
			}
			
			control.add(envOp);
			stack.add(envOp);
			control.loadCS(csStore.getControlStructure(expId));
			return;
		}
		if (control.peek().getType() == NodeType.gamma &&
				stack.peek().getType() == NodeType.identifier) {
			control.pop();
			ControlOperator identifier = stack.pop();
			ControlOperator value = stack.pop();
			switch (identifier.getName()) {
			case "Print":
				if (value.getType() == NodeType.integer) {
					System.out.println(value.getIntegerValue());
				}
				else if (value.getType() == NodeType.string) {
					System.out.println(value.getStringValue());
				}
				// TODO: check for tuple type
				stack.add(new ControlOperator(new DummyNode("Generated Node")));
				break;

			default:
				break;
			}
			return;
		}
		if (stack.peek().getType() == NodeType.op_plus) {
			stack.pop();
			int val1 = stack.pop().getIntegerValue();
			int val2 = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer, val1+val2));
			return;
		}
		
		
		if(control.peek().getType() == NodeType.env_operator && 
				stack.getStack().get(1).getType() == NodeType.env_operator) {
			EnvironmentOperator env1 = (EnvironmentOperator) control.peek();
			EnvironmentOperator env2 = (EnvironmentOperator) stack.getStack().get(1);
			ControlOperator op = stack.peek();
			if (env1.getId() == env2.getId()) {
				control.pop();
				stack.pop();
				stack.pop();
				stack.add(op);
				currentEnv = currentEnv.getParent();
			}
			return;
		}
		
		stack.add(control.pop());
	}
	

}
