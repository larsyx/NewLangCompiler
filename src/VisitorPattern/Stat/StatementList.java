package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class StatementList extends Node {

    public ArrayList<StatOp> statList;

    public StatementList(StatOp op){
        statList= new ArrayList<>();
        statList.add(0,op);
    }

    public StatementList(StatOp op, StatementList statementList){
        if(statementList ==null || statementList.statList == null)
            statList= new ArrayList<>();
        else
            statList = statementList.statList;

        statList.add(0,op);
    }

    public void addStat(StatOp op){
        statList.add(0,op);
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
