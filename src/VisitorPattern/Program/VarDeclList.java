package VisitorPattern.Program;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class VarDeclList extends Node {
    public ArrayList<VarDeclOp> varDeclOps;

    public VarDeclList(){
        varDeclOps=new ArrayList<>();
    }

    public VarDeclList(VarDeclOp op , VarDeclList varDeclList){
        if( varDeclList == null || varDeclList.varDeclOps == null)
            varDeclOps = new ArrayList<>();
        else
            varDeclOps = varDeclList.varDeclOps;

        this.addVarDeclOp(op);
    }

    public void addVarDeclOp(VarDeclOp op){
        varDeclOps.add(op);
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
