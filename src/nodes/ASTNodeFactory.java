package nodes;

public class ASTNodeFactory {
	public ASTNode makeNode(String rawInput, String type){
	
	      if(type.equals("let")){
	         return new LetNode(rawInput);	     
	      }
	      if(type.equals("lambda")){
		         return new LambdaNode(rawInput);	     
	      }
	      if(type.equals("function_form")){
		     return new FunctionFormNode(rawInput);	     
		  }
	      if(type.equals("where")){
			     return new WhereNode(rawInput);	     
		  }
	      if(type.equals("tau")){
			     return new TauNode(rawInput);	     
		  }
	      if(type.equals("aug")){
			     return new ASTNode(rawInput,NodeType.aug);	     
		  }
	      if(type.equals("->")){
			     return new ASTNode(rawInput,NodeType.cond);	     
		  }
	      if(type.equals("or")){
			     return new ASTNode(rawInput,NodeType.op_or);	     
		  }
	      if(type.equals("&")){
			     return new ASTNode(rawInput, NodeType.op_and);	     
		  }
	      if(type.equals("not")){
			     return new ASTNode(rawInput,NodeType.op_not);	     
		  }
	      if(type.equals("gr")){
			     return new ASTNode(rawInput,NodeType.op_gr);	     
		  }
	      if(type.equals("ge")){
			     return new ASTNode(rawInput,NodeType.op_ge);	     
		  }
	      if(type.equals("ls")){
			     return new ASTNode(rawInput,NodeType.op_ls);	     
		  }
	      if(type.equals("le")){
			     return new ASTNode(rawInput,NodeType.op_le);	     
		  }
	      if(type.equals("eq")){
			     return new ASTNode(rawInput,NodeType.op_eq);	     
		  }
	      if(type.equals("ne")){
			     return new ASTNode(rawInput,NodeType.op_ne);	     
		  }
	      if(type.equals("+")){
			     return new ASTNode(rawInput,NodeType.op_plus);	     
		  }
	      if(type.equals("-")){
			     return new ASTNode(rawInput,NodeType.op_minus);	     
		  }
	      if(type.equals("neg")){
			     return new ASTNode(rawInput,NodeType.op_neg);	     
		  }
	      if(type.equals("*")){
			     return new ASTNode(rawInput,NodeType.op_mul);	     
		  }
	      if(type.equals("**")){
			     return new ASTNode(rawInput,NodeType.op_pow);	     
		  }
	      if(type.equals("/")){
			     return new ASTNode(rawInput,NodeType.op_div);	     
		  }
	      if(type.equals("@")){
			     return new AtNode(rawInput);	     
		  }
	      if(type.equals("gamma")){
			     return new GammaNode(rawInput);	     
		  }
	      if(type.equals("<true>")){
			     return new ASTNode(rawInput, NodeType.true_value);	     
		  }
	      if(type.equals("<false>")){
			     return new ASTNode(rawInput,NodeType.false_value);	     
		  }
	      if(type.equals("<nil>")){
			     return new ASTNode(rawInput,NodeType.nil);	     
		  }
	      if(type.equals("dummy")){
			     return new ASTNode(rawInput,NodeType.dummy);	     
		  }
	      if(type.equals("within")){
			     return new WithinNode(rawInput);	     
		  }
	      if(type.equals("and")){
			     return new AndNode(rawInput);	     
		  }
	      if(type.equals("rec")){
			     return new RecNode(rawInput);	     
		  }
	      if(type.equals("=")){
			     return new ASTNode(rawInput,NodeType.equal);	     
		  }
	      if(type.equals("<()>")){
			     return new ASTNode(rawInput, NodeType.empty_params);	     
		  }
	      if(type.equals(",")){
			     return new ASTNode(rawInput,NodeType.comma);	     
		  }
	      if(type.length()>5) {
	    	  if(type.substring(0, 4).equals("<ID:")) {
	    		  return new IdentifierNode(rawInput,type.substring(4,type.length()-1));
	    	  }
	      }
	      if(type.length()>6) {
	    	  if(type.substring(0, 5).equals("<INT:")) {
	    		  return new IntegerNode(rawInput,type.substring(5,type.length()-1));
	    	  }
	      }
	      if(type.length()>=8) {
	    	  if(type.substring(0, 5).equals("<STR:")) {
	    		  String stringValue = type.substring(5,type.length()-1);
	    		  if (stringValue.equals("''")) {
					stringValue = "";
				} else {
					stringValue = type.substring(6,type.length()-2);
				}
	    		  
	    		  return new StringNode(rawInput,stringValue);
	    	  }
	      }
	      
	      
	   

	      
	      return new ASTNode(rawInput);
	   }
}
