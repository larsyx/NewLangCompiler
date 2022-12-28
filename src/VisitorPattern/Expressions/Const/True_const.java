package VisitorPattern.Expressions.Const;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Visitor;

public class True_const extends Node implements Exp, Const {
    public boolean true_const = true;

    public  True_const(){
        true_const = true;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
