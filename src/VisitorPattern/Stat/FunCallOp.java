package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Expressions.ExpressionList;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Visitor;

public class FunCallOp extends Node implements StatOp, Exp{
    public Identifier id;
    public ExpressionList exprList;

    public FunCallOp(Identifier id, ExpressionList exprList) {
        this.id = id;
        this.exprList = exprList;
    }

    public FunCallOp(Identifier id) {
        this.id = id;
        this.exprList = null;
    }
    public void addExp(Exp exp){
        this.exprList.addExp(exp);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
