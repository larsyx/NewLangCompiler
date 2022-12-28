package main;

import Parser.Lexer;
import Parser.parser;
import VisitorPattern.PrintSintaxTree;
import VisitorPattern.Program.ProgramOp;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class NewLangStarter {

    public static void main(String[] args){
        try {
            parser p = new parser(new Lexer(new FileReader(args[0])));
 //           System.out.println(p.debug_parse().value.toString());
            ProgramOp op = (ProgramOp) p.parse().value;
            PrintSintaxTree t = new PrintSintaxTree();
            String result = (String) op.accept(t) ;
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
