package VisitorPattern.Expressions.Const;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class False_const extends Node implements Exp, Const {
    public boolean false_const = false;

    public False_const() {
        this.false_const = false;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
