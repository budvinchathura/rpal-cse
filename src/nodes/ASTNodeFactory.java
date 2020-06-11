package nodes;

public class ASTNodeFactory {
	public ASTNode makeNode(String rawInput, String type){
	
	      if(type.equals("let")){
	         return new LetNode(rawInput);	     
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

	      
	      return new ASTNode(rawInput);
	   }
}
