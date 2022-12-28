package VisitorPattern;

import SymbolTable.SemanticErrorException;

public class Node {
    public Object accept(Visitor v ) throws SemanticErrorException {
        return null;
    }

}
