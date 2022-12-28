package VisitorPattern.Stat;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Expressions.IdentifierList;
import VisitorPattern.Visitor;

public class ReadOp extends Node implements StatOp{

    public IdentifierList idList;

    public ReadOp(IdentifierList ids) {
        this.idList = ids;
    }

    public void addId(Identifier id){
        this.idList.addId(id);
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
