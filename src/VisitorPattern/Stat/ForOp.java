package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Program.BodyOp;
import VisitorPattern.Node;
import VisitorPattern.Expressions.Identifier;
import VisitorPattern.Expressions.Const.Integer_const;
import VisitorPattern.Visitor;

public class ForOp extends Node implements StatOp {
    public Identifier id;
    public Integer_const intConst;
    public Integer_const toIntConst;
    public BodyOp bodyOp;

    public ForOp(Identifier id, Integer_const intConst, Integer_const toIntConst, BodyOp bodyOp) {
        this.id = id;
        this.intConst = intConst;
        this.toIntConst = toIntConst;
        this.bodyOp = bodyOp;
    }

    public Object accept(Visitor v ) throws SemanticErrorException {
        return v.visit(this);
    }
}
