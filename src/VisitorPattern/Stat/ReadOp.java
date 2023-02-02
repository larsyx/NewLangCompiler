package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.Const.String_const;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Expressions.IdentifierList;
import VisitorPattern.Visitor;

public class ReadOp extends Node implements StatOp{

    public IdentifierList idList;
    public String_const string_const;

    public ReadOp(IdentifierList ids, String_const string_const){
        this.idList = ids;
        this.string_const = string_const;
    }

    public ReadOp(IdentifierList ids) {
        this.idList = ids;
    }

    public void addId(Identifier id){
        this.idList.addId(id);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
