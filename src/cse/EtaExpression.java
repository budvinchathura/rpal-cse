package cse;


import nodes.NodeType;

// operator processing recursion
public class EtaExpression extends ControlOperator {

	private int id;
	private String boundedFunctionName;
	private LambdaExpression lambdaExp;
	private int envId;
	public EtaExpression(LambdaExpression lambdaExp) throws Exception {
		if (lambdaExp.getVariables().size()!=1) {
			throw new Exception("eta expression can only handle one function");
		}
		this.lambdaExp = lambdaExp;
		this.boundedFunctionName = lambdaExp.getVariables().get(0);
		this.id = lambdaExp.getId();
		this.envId = lambdaExp.getEnvId();
		this.type = NodeType.eta_expression;
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getEnvId() {
		return this.envId;
	}
	
	public void setEnvId(int id) {
		this.envId = id;
	}
	
	public String getFunctionName(){
		return this.boundedFunctionName;
	}
	
	public LambdaExpression getLambdaExpression() {
		return this.lambdaExp;
	}

}
