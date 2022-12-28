package VisitorPattern.Expressions.Const;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class Integer_const extends Node implements Exp, Const {
    public int attrib;

    public Integer_const(int attrib) {
        this.attrib = attrib;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
