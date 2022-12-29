package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Expressions.ExpressionList;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Expressions.IdentifierList;
import VisitorPattern.Visitor;

public class AssignOp extends Node implements StatOp{
    public IdentifierList idList;
    public ExpressionList exprList;

    public AssignOp(IdentifierList ids, ExpressionList expr) {
        this.idList = ids;
        this.exprList = expr;

    }

    public void addId(Identifier id){
        this.idList.addId(id);
    }

    public void addExp(Exp exp){
        this.exprList.addExp(exp);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
