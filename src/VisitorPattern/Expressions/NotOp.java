package VisitorPattern.Expressions;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class NotOp extends Node implements Exp{
    public Exp exp;

    public NotOp(Exp exp) {
        this.exp = exp;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
