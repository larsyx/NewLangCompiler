package VisitorPattern.Expressions;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class Identifier extends Node implements Exp{
    public String attrib;

    public Identifier(String attrib) {
        this.attrib = attrib;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "attrib='" + attrib + '\'' +
                '}';
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
