package VisitorPattern.Stat;

import VisitorPattern.Expressions.Exp;
import VisitorPattern.Program.BodyOp;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class WhileOp extends Node implements StatOp{
    public Exp expression;
    public BodyOp bodyOp;

    public WhileOp(Exp expression, BodyOp bodyOp) {
        this.expression = expression;
        this.bodyOp = bodyOp;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
