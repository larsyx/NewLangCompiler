package VisitorPattern.Stat;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Visitor;

public interface StatOp {
    public Object accept(Visitor v ) throws SemanticErrorException;
}
