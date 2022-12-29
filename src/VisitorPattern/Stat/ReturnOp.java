package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class ReturnOp extends Node implements StatOp{
    public Exp expression;

    public ReturnOp(Exp expression) {
        this.expression = expression;
    }

    public ReturnOp() {
        this.expression = null;
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
