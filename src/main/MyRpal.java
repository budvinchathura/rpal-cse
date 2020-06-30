package main;

import java.util.ArrayList;

import cse.ControlStrcutureStore;
import cse.ControlStructure;
import cse.Executer;

public class MyRpal {

	public static void main(String[] args) {
		System.out.println("RPAL CSE machine....\n");
		
		// read from file
        ASTFileReader reader = new ASTFileReader(args[0]);
        ArrayList<String> rawAST = reader.getAST();
        
        // make AST graph from raw string values
        AST ast = new AST(rawAST);

        try {
			ast.standardize();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
        
        
        // after standardizing, nodes may be in different depth levels than previous
        // update their new depths
        ast.updateDepth();
        
        
//        ast.printAST();
        
        // store all control structures in a store as key value pairs
        ControlStrcutureStore store = new ControlStrcutureStore();
        try {
			ControlStructure rootCS = new ControlStructure(ast.getRoot(), store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // executer will apply CSE rules
        Executer executer = new Executer(store);
        try {
			executer.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//        System.out.println("done");
        

	}

}
