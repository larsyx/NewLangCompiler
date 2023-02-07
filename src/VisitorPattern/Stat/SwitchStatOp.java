package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.Const.Const;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class SwitchStatOp extends Node implements StatOp {
    public Identifier id;
    public Const c1;
    public StatementList st1;
    public Const c2;
    public StatementList st2;
    public Const c3;
    public StatementList st3;


    public SwitchStatOp(Identifier id, Const c1, StatementList st1, Const c2, StatementList st2, Const c3, StatementList st3){
        this.id = id;
        this.c1=c1;
        this.st1=st1;
        this.c2 = c2;
        this.st2 = st2;
        this.c3 = c3;
        this.st3 = st3;
    }


    @Override
    public Object accept(Visitor v) throws SemanticErrorException {
        return v.visit(this);
    }

}
