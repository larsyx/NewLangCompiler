package VisitorPattern.Expressions;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class ExpressionList extends Node {
    public ArrayList<Exp> expList;

    public ExpressionList(Exp exp){
        expList = new ArrayList<>();
        expList.add(0,exp);
    }

    public ExpressionList(Exp exp, ExpressionList expressionList){
        if(expressionList == null || expressionList.expList == null)
            expList = new ArrayList<>();
        else
            expList = expressionList.expList;
        expList.add(0,exp);
    }

    public void addExp(Exp exp){
        expList.add(0,exp);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
