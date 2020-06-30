package cse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import nodes.ASTNode;
import nodes.NodeType;

// Class for applying rules in CSE machine
public class Executer {
	private Control control;
	private Stack stack;
	private ControlStrcutureStore csStore;
	private int nextEnvId;
	private Environment currentEnv;
	private EnvStore envStore;
	private ArrayList<Integer> envStack;
	private String[] _builtInFunctions = new String[]{"Print", "Order","Conc","Stem","Stern","Null","Isinteger", "Istruthvalue", "Isstring", "Istuple", "Isfunction", "Isdummy","ItoS"};
	private List<String> builtInFunctions = Arrays.asList(_builtInFunctions);

	public Executer(ControlStrcutureStore csStore) {
		this.control = new Control();
		this.stack = new Stack();
		this.csStore = csStore;
		this.envStore = new EnvStore();
		this.envStack = new ArrayList<>();
		this.nextEnvId = 0;
		EnvironmentOperator envOp = new EnvironmentOperator(nextEnvId);
		this.currentEnv = new Environment(null, nextEnvId);
		this.envStore.addEnv(nextEnvId, currentEnv);
		this.envStack.add(nextEnvId);
		this.nextEnvId+=1;
		control.add(envOp);
		stack.add(envOp);
		control.loadCS(this.csStore.getControlStructure(0));
	}
	
	public void execute() throws Exception {
		// apply CSE rules iteratively
		while (this.control.getLength()>0 && this.stack.getLength()>0) {
			this.process();
		}
	}
	
	private void process() throws Exception {
		
		// if identifier is on top of control 
		// CSE rule 1
		if (control.peek().getType() == NodeType.identifier) {
			ControlOperator op = control.pop();
			ControlOperator value;
			
			if (! builtInFunctions.contains(op.getName())) {
				value = currentEnv.search(op.getName());
			}else {
				// if the identifier is a built-in function, just add to stack
				value = op;
			}
			stack.add(value);
			return;
		}
		
		// CSE rule 2
		if (control.peek().getType() == NodeType.lambda_expression) {
			LambdaExpression lambdaExp = (LambdaExpression) control.pop();
			lambdaExp.setEnvId(currentEnv.getId());		//set current environment to lambda expression
			stack.add(lambdaExp);
			return;			
		}
		
		// CSE rule 4
		// creating a new environment for the lambda expression
		if (control.peek().getType() == NodeType.gamma &&
				stack.peek().getType() == NodeType.lambda_expression) {
			control.pop();
			LambdaExpression lambdaExp = (LambdaExpression) stack.pop();
			int expId = lambdaExp.getId();
			
			// make a new environment
			EnvironmentOperator envOp = new EnvironmentOperator(nextEnvId);
			this.currentEnv = new Environment(this.envStore.getEnv(lambdaExp.getEnvId()), nextEnvId);
			envStore.addEnv(nextEnvId, currentEnv);
			this.envStack.add(nextEnvId);
			this.nextEnvId+=1;
			
			// multiple names are bounded in lambda expression
			// CSE rule 11
			if (lambdaExp.getVariables().size()>1) {
				if (stack.peek().getType()!=NodeType.tuple) {
					throw new Exception("could not find a tuple to bind several variables");
				}
				
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
			
			//add elements from new control structure
			control.loadCS(csStore.getControlStructure(expId));
			return;
		}
		
		//CSE rule 3
		if (control.peek().getType() == NodeType.gamma &&
				stack.peek().getType() == NodeType.identifier) {
			control.pop();
			ControlOperator identifier = stack.pop();
			ControlOperator op = stack.pop();
			
			//built-in functions
			switch (identifier.getName()) {
			case "Print":
				System.out.println(this.getPrintableString(op));
				
				//dummy node should be added after printing
				stack.add(new ControlOperator(new ASTNode(NodeType.dummy)));
				break;
			case "Order":
				stack.add(new ControlOperator(NodeType.integer,((TupleOperator)op).getSize()));
				break;
			
			case "Conc":
				// Conc has 2 gamma nodes
				// remove the second gamma
				control.pop();
				ControlOperator op2 = stack.pop();
				stack.add(new ControlOperator(NodeType.string,op.getStringValue()+op2.getStringValue()));
				break;
			
			case "Stem":
				String string1 = op.getStringValue();
				if (string1.isEmpty()) {
					stack.add(new ControlOperator(NodeType.string,""));
				}else {
					stack.add(new ControlOperator(NodeType.string,string1.substring(0, 1)));
				}
				break;
			
			case "Stern":
				String string2 = op.getStringValue();
				if (string2.isEmpty() || string2.length()==1) {
					stack.add(new ControlOperator(NodeType.string,""));
				}else {
					stack.add(new ControlOperator(NodeType.string,string2.substring(1)));
				}
				break;
			
			case "Null":
				stack.add(this.makeBooleanOp(op.getType()==NodeType.nil));
				break;
				
			case "Isinteger":
				stack.add(this.makeBooleanOp(op.getType()==NodeType.integer));
				break;
				
			case "Isstring":
				stack.add(this.makeBooleanOp(op.getType()==NodeType.string));
				break;
				
			case "Istruthvalue":
				stack.add(this.makeBooleanOp(
						op.getType()==NodeType.true_value ||  op.getType()==NodeType.false_value));
				break;
				
			case "Isfunction":
				stack.add(this.makeBooleanOp(op.getType()==NodeType.lambda_expression));
				break;
				
			case "Istuple":
				stack.add(this.makeBooleanOp(op.getType()==NodeType.tuple));
				break;
				
			case "Isdummy":
				stack.add(this.makeBooleanOp(op.getType()==NodeType.dummy));
				break;
				
			case "ItoS":
				stack.add(new ControlOperator(NodeType.string,Integer.toString(op.getIntegerValue())));
				break;
				
			default:
				break;
			}
			return;
		}
		
		// CSE rule 9
		if (control.peek().getType() == NodeType.tau) {
			TauOperator tau =  (TauOperator) control.pop();
			ArrayList<ControlOperator> items = new ArrayList<>();
			
			for (int i = 0; i < tau.getN(); i++) {
				items.add(stack.pop());
			}
			
			TupleOperator tuple = new TupleOperator(items);
			stack.add(tuple);
			return;
		}
		
		//CSE rule 10
		if (control.peek().getType() == NodeType.gamma
				&& stack.getStack().get(0).getType() == NodeType.tuple) {
			ControlOperator selector = stack.getStack().get(1);
			if (selector.getType() != NodeType.integer) {
				throw new Exception("Tuple selector must be an integer value");
			}
			control.pop();
			TupleOperator tuple = (TupleOperator) stack.pop();
			int selectorValue = stack.pop().getIntegerValue();
			stack.add(tuple.getTuple().get(selectorValue-1));
			return;
		}
		
		// CSE rule 6 for aug operator
		if (control.peek().getType() == NodeType.aug) {
			control.pop();
			ControlOperator op1 = stack.pop();
			ControlOperator op2 = stack.pop();
			if (op1.getType() == NodeType.nil) {
				ArrayList<ControlOperator> newTupleValue = new ArrayList<>();
				newTupleValue.add(op2);
				stack.add(new TupleOperator(newTupleValue));
			}else if(op1.getType() == NodeType.tuple){
				ArrayList<ControlOperator> newTupleValue = ((TupleOperator) op1).getTuple();
				newTupleValue.add(op2);
				stack.add(new TupleOperator(newTupleValue));
			}else {
				// can not aug with other data types
				throw new Exception("First operand for aug must be nil or a tuple");
			}
			return;
		}
		
		// CSE rule 6 for +
		if (control.peek().getType() == NodeType.op_plus) {
			control.pop();
			int val1 = stack.pop().getIntegerValue();
			int val2 = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer, val1+val2));
			return;
		}
		// CSE rule 6 for -
		if (control.peek().getType() == NodeType.op_minus) {
			control.pop();
			int val1 = stack.pop().getIntegerValue();
			int val2 = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer, val1-val2));
			return;
		}
		// CSE rule 6 for *
		if (control.peek().getType() == NodeType.op_mul) {
			control.pop();
			int val1 = stack.pop().getIntegerValue();
			int val2 = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer, val1*val2));
			return;
		}
		// CSE rule 6 for /
		if (control.peek().getType() == NodeType.op_div) {
			control.pop();
			int val1 = stack.pop().getIntegerValue();
			int val2 = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer, val1/val2));
			return;
		}
		// CSE rule 6 for **
		if (control.peek().getType() == NodeType.op_pow) {
			control.pop();
			int val1 = stack.pop().getIntegerValue();
			int val2 = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer,(int) Math.pow(val1, val2)));
			return;
		}
		// CSE rule 7 for neg
		if (control.peek().getType() == NodeType.op_neg) {
			control.pop();
			int val = stack.pop().getIntegerValue();
			stack.add(new ControlOperator(NodeType.integer,-1*val));
			return;
		}
		
		// and,or binops 
		// not unop
		if (control.peek().getType() == NodeType.op_or
				|| control.peek().getType() == NodeType.op_and
				|| control.peek().getType() == NodeType.op_not) {
			
			NodeType type = control.pop().getType();
			
			if (type == NodeType.op_not) {
				
				Boolean val = stack.pop().getBooleanValue();
				stack.add(this.makeBooleanOp(!val));
			
			}else {
				Boolean val1 = stack.pop().getBooleanValue();
				Boolean val2 = stack.pop().getBooleanValue();
				Boolean result = null;
				switch (type) {
				case op_or:
					result = val1 || val2;
					break;
				case op_and:
					result = val1 && val2;
					break;
				default:
					break;
				}
				stack.add(this.makeBooleanOp(result));
			}			
			return;
		}
		
		// gr,ge,ls,le,eq,ne binops
		if (control.peek().getType() == NodeType.op_gr
				|| control.peek().getType() == NodeType.op_ge
				|| control.peek().getType() == NodeType.op_ls
				|| control.peek().getType() == NodeType.op_le
				|| control.peek().getType() == NodeType.op_eq
				|| control.peek().getType() == NodeType.op_ne) {
			ControlOperator op1 = stack.pop();
			ControlOperator op2 = stack.pop();
			NodeType operation = control.pop().getType();
			Boolean resultValue = false;
			
			if (op1.getType()==NodeType.integer && op2.getType()==NodeType.integer) {
				
				switch (operation) {
				case op_gr:
					resultValue = op1.getIntegerValue() > op2.getIntegerValue();
					break;
					
				case op_ge:
					resultValue = op1.getIntegerValue() >= op2.getIntegerValue();
					break;
					
				case op_ls:
					resultValue = op1.getIntegerValue() < op2.getIntegerValue();
					break;
				
				case op_le:
					resultValue = op1.getIntegerValue() <= op2.getIntegerValue();
					break;
				
				case op_eq:
					resultValue = op1.getIntegerValue() == op2.getIntegerValue();
					break;
					
				case op_ne:
					resultValue = op1.getIntegerValue() != op2.getIntegerValue();
					break;

				default:
					break;
				}
				
			} else if (op1.getType()==NodeType.string && op2.getType()==NodeType.string){
				int lexographicalComp = op1.getStringValue().compareTo(op2.getStringValue());
				
				switch (operation) {
				case op_gr:
					resultValue = lexographicalComp>0;
					break;
					
				case op_ge:
					resultValue = lexographicalComp>=0;
					break;
					
				case op_ls:
					resultValue = lexographicalComp<0;
					break;
				
				case op_le:
					resultValue = lexographicalComp<=0;
					break;
				
				case op_eq:
					resultValue = lexographicalComp==0;
					break;
					
				case op_ne:
					resultValue = lexographicalComp!=0;
					break;

				default:
					break;
				}
			}else {
				throw new Exception("unsupported operands for "+operation);
			}
			stack.add(this.makeBooleanOp(resultValue));
			return;
			
		}
		
		// CSE rule 8
		if (control.peek().getType()==NodeType.beta_operator) {
			BetaOperator betaOp = (BetaOperator) control.pop();
			Boolean truthValue = stack.pop().getBooleanValue();
			
			control.loadCS(csStore.getControlStructure(betaOp.getCSid(truthValue)));
			return;
			
		}
		
		// CSE rule 12
		if (control.peek().getType()==NodeType.gamma
				&& stack.getStack().get(0).getType()==NodeType.y_star
				&& stack.getStack().get(1).getType()==NodeType.lambda_expression) {
			control.pop();
			stack.pop();
			EtaExpression etaExpression = new EtaExpression((LambdaExpression) stack.pop());
			stack.add(etaExpression);
			return;
		}
		
		// CSE rule 13
		if (control.peek().getType()==NodeType.gamma
				&& stack.peek().getType()==NodeType.eta_expression) {
			control.add(new ControlOperator(NodeType.gamma));
			EtaExpression etaExpression = (EtaExpression) stack.peek();
			stack.add(etaExpression.getLambdaExpression());
			
			return;
		}
		
		
		//CSE rule 5
		// exit environment
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
				envStack.remove(envStack.size()-1);
				if (envStack.size()>0) {
					currentEnv = envStore.getEnv(envStack.get(envStack.size()-1));
				}else {
					currentEnv = null;
				}
				
			}
			return;
		}
		
		//if nothing matched for boave rules, just execute CSE rule 1
		stack.add(control.pop());
	}
	
	private ControlOperator makeBooleanOp(Boolean truthValue) {
		if (truthValue) {
			return new ControlOperator(NodeType.true_value);
		}else {
			return new ControlOperator(NodeType.false_value);
		}
	}
	
	// Printing different types
	private String getPrintableString(ControlOperator op) {
		switch (op.type) {
		case nil:
			return "nil";
		case dummy:
			return "dummy";
			
		case integer:
			return Integer.toString(op.getIntegerValue());
			
		case string:
			return op.getStringValue();
			
		case true_value:
		case false_value:
			return Boolean.toString(op.getBooleanValue());
		case tuple:
			StringJoiner joinedOutput = new StringJoiner(", ");
			ArrayList<ControlOperator> items = ((TupleOperator)op).getTuple();
			for (ControlOperator item : items) {
				joinedOutput.add(this.getPrintableString(item));
			}
			return "("+joinedOutput.toString()+")";

		case lambda_expression:
			ArrayList<String> variables =  ((LambdaExpression) op).getVariables();
			String variable = "";
			if (variables.size()>0) {
				variable = variables.get(0);
			}
			int id = ((LambdaExpression) op).getId();
			return "[lambda closure: "+variable+": "+id+"]";

		default:
			return "";
		}
	}

}
