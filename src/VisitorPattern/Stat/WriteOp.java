package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Expressions.ExpressionList;
import VisitorPattern.Visitor;

public class WriteOp extends Node implements StatOp{

    public boolean newLine;
    public ExpressionList exprList;


    public WriteOp( boolean nl, ExpressionList expr ) {
        this.exprList = expr;
        this.newLine = nl;
    }

    public void addExp(Exp exp){
        this.exprList.addExp(exp);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
