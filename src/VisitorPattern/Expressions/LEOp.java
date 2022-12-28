package VisitorPattern.Expressions;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class LEOp extends Node implements Exp{
    public Exp left, right;

    public LEOp(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
