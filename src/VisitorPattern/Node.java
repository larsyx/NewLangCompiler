package VisitorPattern;

import SymbolTable.SemanticErrorException;
import SymbolTable.SymbolTable;

public class Node {
    public Object accept(Visitor v ) throws SemanticErrorException {
        return null;
    }

}
