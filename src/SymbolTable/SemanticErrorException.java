package SymbolTable;

public class SemanticErrorException extends Exception{
    public SemanticErrorException() {
        super("Errore semantico");
        this.printStackTrace();
    }

    public SemanticErrorException(String message) {
        super("Errore semantico: " + message);
        this.printStackTrace();
    }
}
