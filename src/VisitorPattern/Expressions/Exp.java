package VisitorPattern.Expressions;

import VisitorPattern.Visitor;

public interface Exp {
    public Object accept(Visitor v );
}
