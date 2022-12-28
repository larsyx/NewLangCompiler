package VisitorPattern.Program;

import VisitorPattern.Node;
import VisitorPattern.Expressions.IdentifierList;
import VisitorPattern.Visitor;

public class ParDeclOp extends Node {
    public String outOrIn;
    public String type;
    public IdentifierList id;

    public ParDeclOp(String outOrIn, String type, IdentifierList ids) {
        this.outOrIn = outOrIn;
        this.type = type;
        this.id = ids;
    }


    public Object accept(Visitor v ){
        return v.visit(this);
    }

}
