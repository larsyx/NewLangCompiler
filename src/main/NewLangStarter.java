package main;

import Parser.Lexer;
import Parser.parser;
import SymbolTable.CreateSymbolTable;
import TypeChecking.TypeChecking;
import VisitorPattern.PrintSintaxTree;
import VisitorPattern.Program.ProgramOp;
import Traduzione.CTranslate;

import java.io.*;

public class NewLangStarter {

    public static void main(String[] args){
        try {
            parser p = new parser(new Lexer(new FileReader(args[0])));

            ProgramOp op = (ProgramOp) p.parse().value;

            CreateSymbolTable cst = new CreateSymbolTable();
            op.accept(cst);


            PrintSintaxTree t = new PrintSintaxTree();
            String result = (String) op.accept(t) ;
            //System.out.println(result);

            TypeChecking tc = new TypeChecking();
            op.accept(tc);
            tc.checkFunCallParam();
            String str = "";
            CTranslate ct = new CTranslate();
            String traslate = (String) op.accept(ct);
            System.out.println(traslate);

            File file = newFile();
            scriviFile(file, traslate);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void scriviFile(File file, String traslate) {
        try {
            FileWriter fileWriter= new FileWriter(file);
            fileWriter.write(traslate);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File newFile() {
        String path = "./compileResult/programma.c";
        try {
            File file = new File(path);
            if (file.exists())
                file.delete();

            file.createNewFile();
            System.out.println("Il file " + path + " è stato creato");
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
