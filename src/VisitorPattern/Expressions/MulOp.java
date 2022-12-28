package VisitorPattern.Expressions;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class MulOp extends Node implements Exp{
    public Exp left, right;

    public MulOp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
