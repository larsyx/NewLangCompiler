package VisitorPattern.Program;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Visitor;

public class FunOp extends Node {
    public Identifier id;
    public ParamDeclList paramDeclList;
    public String type;
    public BodyOp body;
    public boolean isMain;

    public FunOp(Identifier id, ParamDeclList ops, String type, BodyOp body) {
        this.id = id;
        this.paramDeclList = ops;
        this.type = type;
        this.body = body;
        this.isMain = false;
    }

    public void isMain(){
        this.isMain = true;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
