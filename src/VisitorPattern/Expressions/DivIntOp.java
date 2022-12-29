package VisitorPattern.Expressions;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class DivIntOp extends Node implements Exp{
    public Exp left, right;

    public DivIntOp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
