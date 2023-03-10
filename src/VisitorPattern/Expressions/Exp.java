package VisitorPattern.Expressions;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Node;
import VisitorPattern.Visitor;

public interface Exp{

    public Object accept(Visitor v ) throws SemanticErrorException;

}
