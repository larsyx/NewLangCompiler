package VisitorPattern;

import SymbolTable.SemanticErrorException;
import SymbolTable.SymbolTable;

public class Node {
    private String type_node;

    public String getType_node() {
        return type_node;
    }

    public void setType_node(String type_node) {
        this.type_node = type_node;
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return null;
    }

}
