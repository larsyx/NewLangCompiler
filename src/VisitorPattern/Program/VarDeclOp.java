package VisitorPattern.Program;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Program.IdInit.InitList;
import VisitorPattern.Visitor;

public class VarDeclOp extends Node {
    public String type;
    public InitList idList;
    public boolean isVar;


    public VarDeclOp(String type, InitList idList) {
        this.type = type;
        this.idList = idList;
        this.isVar = false;
    }

    public VarDeclOp(InitList idList){
        this.isVar= true;
        this.type= null;
        this.idList = idList;
    }

    public void setVar(boolean var) {
        isVar = var;
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
