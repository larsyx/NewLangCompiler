package VisitorPattern.Program.IdInit;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public interface InitList {
    public void addIdInit(IdInit idInit);

    public Object accept(Visitor v ) throws SemanticErrorException;
}
