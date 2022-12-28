package VisitorPattern.Program;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class DeclList extends Node {
    public ArrayList<VarDeclOp> varDeclList;
    public ArrayList<FunOp> funDeclList;

    public DeclList(){
        this.varDeclList = new ArrayList<>();
        this.funDeclList = new ArrayList<>();
    }

    public DeclList(VarDeclOp op, DeclList dl) {
        if (dl == null || dl.varDeclList == null ){
            this.varDeclList = new ArrayList<>();
        }else {
            this.varDeclList = dl.varDeclList;
            this.funDeclList = dl.funDeclList;
        }

        this.addVarDecl(op);
    }

    public DeclList(FunOp op, DeclList dl) {
        if (dl == null || dl.funDeclList == null ){
            this.funDeclList = new ArrayList<>();
        }else {
            this.varDeclList = dl.varDeclList;
            this.funDeclList = dl.funDeclList;
        }

        this.addFunDecl(op);
    }

    public void addVarDecl(VarDeclOp op){
        varDeclList.add(0,op);
    }

    public void addFunDecl(FunOp op){
        funDeclList.add(0,op);
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
