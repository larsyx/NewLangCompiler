package VisitorPattern.Program;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class ParamDeclList extends Node {
    public ArrayList<ParDeclOp> parDeclOps;

    public ParamDeclList(ParDeclOp op){
        parDeclOps = new ArrayList<>();
        parDeclOps.add(0,op);
    }

    public ParamDeclList(){
        parDeclOps = new ArrayList<>();
    }

    public ParamDeclList(ParDeclOp op, ParamDeclList list){
        if(list == null || list.parDeclOps == null)
            parDeclOps = new ArrayList<>();
        else
            parDeclOps = list.parDeclOps;

        this.addParDeclOp(op);
    }

    public void addParDeclOp(ParDeclOp op){
        this.parDeclOps.add(op);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
