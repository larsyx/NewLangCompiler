package VisitorPattern.Program;

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
        this.type= "";
        this.idList = idList;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
