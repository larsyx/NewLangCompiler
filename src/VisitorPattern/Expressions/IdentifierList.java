package VisitorPattern.Expressions;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class IdentifierList extends Node {

    public ArrayList<Identifier> ids;

    public IdentifierList(Identifier id){
        ids = new ArrayList<>();
        ids.add(0,id);
    }

    public IdentifierList(Identifier id, IdentifierList identifierList){
        if(identifierList == null || identifierList.ids == null)
            ids = new ArrayList<>();
        else
            ids = identifierList.ids;
        ids.add(0,id);
    }
    public void addId(Identifier id){
        this.ids.add(0,id);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
