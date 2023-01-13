package VisitorPattern.Program;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Node;
import VisitorPattern.Expressions.IdentifierList;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class ParDeclOp extends Node {
    public String outOrIn;
    public String type;
    public IdentifierList id;

    public ParDeclOp(String outOrIn, String type, IdentifierList ids) {
        this.outOrIn = outOrIn;
        this.type = type;
        this.id = ids;
        reverseIds();
    }

    private void reverseIds(){
        if(id !=null && id.ids !=null && id.ids.size()>1) {
            ArrayList<Identifier> newIds = new ArrayList<>();
            for(Identifier i : id.ids)
                newIds.add(0,i);

            id.ids=newIds;
        }
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }

}
