package main;

import Parser.Lexer;
import Parser.parser;
import SymbolTable.CreateSymbolTable;
import VisitorPattern.PrintSintaxTree;
import VisitorPattern.Program.ProgramOp;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class NewLangStarter {

    public static void main(String[] args){
        try {
            parser p = new parser(new Lexer(new FileReader(args[0])));

            ProgramOp op = (ProgramOp) p.parse().value;
            PrintSintaxTree t = new PrintSintaxTree();
            String result = (String) op.accept(t) ;
            //System.out.println(result);

            CreateSymbolTable cst = new CreateSymbolTable();
            op.accept(cst);

            String str = "";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
