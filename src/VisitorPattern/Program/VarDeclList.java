package VisitorPattern.Program;

import SymbolTable.SemanticErrorException;
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

    public void removeVarDeclOp(VarDeclOp op){
        varDeclOps.remove(op);
    }
    public void addVarDeclOp(VarDeclOp op){
        varDeclOps.add(0, op);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }

}
