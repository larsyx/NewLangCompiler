package VisitorPattern.Program.IdInit;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class IdInitObblList extends Node implements InitList{
    public ArrayList<IdInit> idInits;

    public IdInitObblList(IdInit id){
        idInits = new ArrayList<>();
        idInits.add(0,id);
    }

    public IdInitObblList(IdInit id, IdInitObblList initList){
        if(initList==null || initList.idInits == null)
            idInits = new ArrayList<>();
        else
            idInits = initList.idInits;

        idInits.add(0,id);
    }

    public void addIdInit(IdInit id){
        idInits.add(0,id);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}

