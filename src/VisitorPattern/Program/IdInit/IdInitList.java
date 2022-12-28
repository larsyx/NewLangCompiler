package VisitorPattern.Program.IdInit;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class IdInitList extends Node implements InitList{
    public ArrayList<IdInit> idInits;

    public IdInitList(IdInit id){
        idInits = new ArrayList<>();
        idInits.add(0,id);
    }

    public IdInitList(IdInit id, IdInitList initList){
        if(initList == null || initList.idInits == null)
            idInits = new ArrayList<>();
        else
            idInits = initList.idInits;

        idInits.add(0,id);
    }

    public void addIdInit(IdInit id){
        idInits.add(0,id);
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
