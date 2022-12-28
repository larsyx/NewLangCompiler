package VisitorPattern.Expressions.Const;

import VisitorPattern.Expressions.UminusOp;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class Char_const extends Node implements Exp, Const {
    public char attrib;

    public Char_const(char attrib) {
        this.attrib = attrib;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
