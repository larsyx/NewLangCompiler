package VisitorPattern.Program;

import VisitorPattern.Node;
import VisitorPattern.Stat.StatementList;
import VisitorPattern.Visitor;

public class BodyOp extends Node {
    public VarDeclList varDeclList;
    public StatementList statList;

    public BodyOp( VarDeclList v, StatementList s) {
        this.varDeclList = v;
        this.statList = s;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
