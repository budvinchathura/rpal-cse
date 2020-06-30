package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ASTFileReader {
	private String fileName;
    private ArrayList<String> content;

    public ASTFileReader(String fileName) {
        this.fileName = fileName;
        this.content = new ArrayList<String>();
    }
    
    
    // read from file, line by line
    private void processFile() {
        try {
            FileInputStream fis = new FileInputStream(this.fileName);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                this.content.add(sc.nextLine().trim());
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAST() {
        this.processFile();
        return this.content;
    }
}
