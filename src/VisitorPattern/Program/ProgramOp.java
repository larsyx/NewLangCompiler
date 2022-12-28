package VisitorPattern.Program;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class ProgramOp extends Node {
    public DeclList declList_f;
    public FunOp main;
    public DeclList declList_s;


    public ProgramOp(DeclList f, FunOp main, DeclList s) {
        this.declList_f = f;
        this.main = main;
        this.declList_s = s;
    }

    @Override
    public String toString() {
        return "ProgramOp{" +
                "declList_f=" + declList_f +
                ", main=" + main +
                ", declList_s=" + declList_s +
                '}';
    }


    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
