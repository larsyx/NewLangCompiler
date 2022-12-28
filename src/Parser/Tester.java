package Parser;

import java_cup.runtime.Symbol;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws IOException {
        Lexer lexer=new Lexer(new FileReader(args[0]));

        for(Symbol token = lexer.next_token(); token.sym != 0; token = lexer.next_token()){
            System.out.println("Token:" + token.sym + " value: "+ token.value);
        }
    }
}
