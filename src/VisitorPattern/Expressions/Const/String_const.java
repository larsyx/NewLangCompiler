package VisitorPattern.Expressions.Const;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class String_const extends Node implements Exp, Const {
    public String attrib;

    public String_const(String attrib) {
        this.attrib = attrib;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
