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
			     return new AugNode(rawInput);	     
		  }
	      if(type.equals("->")){
			     return new ConditionNode(rawInput);	     
		  }
	      if(type.equals("or")){
			     return new OpOrNode(rawInput);	     
		  }
	      if(type.equals("&")){
			     return new OpAndNode(rawInput);	     
		  }
	      if(type.equals("not")){
			     return new OpNotNode(rawInput);	     
		  }
	      if(type.equals("gr")){
			     return new OpGrNode(rawInput);	     
		  }
	      if(type.equals("ge")){
			     return new OpGeNode(rawInput);	     
		  }
	      if(type.equals("ls")){
			     return new OpLsNode(rawInput);	     
		  }
	      if(type.equals("le")){
			     return new OpLeNode(rawInput);	     
		  }
	      if(type.equals("eq")){
			     return new OpEqNode(rawInput);	     
		  }
	      if(type.equals("ne")){
			     return new OpNeNode(rawInput);	     
		  }
	      if(type.equals("+")){
			     return new OpPlusNode(rawInput);	     
		  }
	      if(type.equals("-")){
			     return new OpMinusNode(rawInput);	     
		  }
	      if(type.equals("neg")){
			     return new OpNegNode(rawInput);	     
		  }
	      if(type.equals("*")){
			     return new OpMulNode(rawInput);	     
		  }
	      if(type.equals("/")){
			     return new OpDivNode(rawInput);	     
		  }
	      if(type.equals("@")){
			     return new AtNode(rawInput);	     
		  }
	      if(type.equals("gamma")){
			     return new GammaNode(rawInput);	     
		  }
	      if(type.equals("true")){
			     return new TrueNode(rawInput);	     
		  }
	      if(type.equals("false")){
			     return new FalseNode(rawInput);	     
		  }
	      if(type.equals("nil")){
			     return new NilNode(rawInput);	     
		  }
	      if(type.equals("dummy")){
			     return new DummyNode(rawInput);	     
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
			     return new EqualNode(rawInput);	     
		  }
	      if(type.equals("()")){
			     return new EmptyParamsNode(rawInput);	     
		  }
	      if(type.equals(",")){
			     return new CommaNode(rawInput);	     
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
	    		  if (stringValue == "''") {
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
