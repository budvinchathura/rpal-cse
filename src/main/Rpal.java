package main;

import java.util.ArrayList;

public class Rpal {

	public static void main(String[] args) {
		System.out.println("RPAL CSE machine....\n");
        ASTFileReader reader = new ASTFileReader(args[0]);
        ArrayList<String> rawAST = reader.getAST();
        AST ast = new AST(rawAST);
        ast.printAST();

	}

}
