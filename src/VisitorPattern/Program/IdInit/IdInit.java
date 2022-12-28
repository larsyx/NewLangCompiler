package VisitorPattern.Program.IdInit;

import VisitorPattern.Node;
import VisitorPattern.Expressions.Const.Const;
import VisitorPattern.Expressions.Exp;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Visitor;

public class IdInit extends Node {
    public Identifier id;
    public Exp assign;
    public Const assignConst;

    public IdInit(Identifier id, Exp assign) {
        this.id = id;
        this.assign = assign;
        assignConst=null;
    }

    public IdInit(Identifier id) {
        this.id = id;
        this.assign = null;
        assignConst=null;
    }

    public IdInit(Identifier id, Const assign) {
        this.id = id;
        this.assignConst = assign;
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
