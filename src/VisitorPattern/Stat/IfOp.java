package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Program.BodyOp;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class IfOp extends Node implements StatOp{
    public Exp expression;
    public BodyOp body;
    public BodyOp elseBody;

    public IfOp(Exp expression, BodyOp body, BodyOp elseBody) {
        this.expression = expression;
        this.body = body;
        this.elseBody = elseBody;
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
