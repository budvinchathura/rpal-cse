package main;

import java.util.ArrayList;

import cse.ControlStrcutureStore;
import cse.ControlStructure;
import cse.Executer;

public class Rpal {

	public static void main(String[] args) {
		System.out.println("RPAL CSE machine....\n");
        ASTFileReader reader = new ASTFileReader(args[0]);
        ArrayList<String> rawAST = reader.getAST();
        AST ast = new AST(rawAST);
//        ast.printAST();
        try {
			ast.standardize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
        ast.updateDepth();
        ast.printAST();
        ControlStrcutureStore store = new ControlStrcutureStore();
        try {
			ControlStructure rootCS = new ControlStructure(ast.getRoot(), store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Executer executer = new Executer(store);
        try {
			executer.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("done");
        

	}

}
