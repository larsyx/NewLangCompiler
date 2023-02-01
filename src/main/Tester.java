package main;

import Parser.Lexer;
import Parser.parser;
import SymbolTable.CreateSymbolTable;
import VisitorPattern.PrintSintaxTree;
import VisitorPattern.Program.ProgramOp;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Tester {

    public static void main(String[] args) throws Exception {
        parser p = new parser(new Lexer(new FileReader(args[0])));

        ProgramOp op = (ProgramOp) p.parse().value;

        CreateSymbolTable cst = new CreateSymbolTable();
        op.accept(cst);

        PrintSintaxTree t = new PrintSintaxTree();
        String result = (String) op.accept(t) ;
     //   System.out.println(result);

    }
}
