package VisitorPattern.Expressions.Const;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class Real_const extends Node implements Exp, Const {
    public float attrib;

    public Real_const(float attrib) {
        this.attrib = attrib;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
