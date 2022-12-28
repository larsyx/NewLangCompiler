package VisitorPattern.Stat;

import VisitorPattern.Visitor;

public interface StatOp {
    public Object accept(Visitor v );
}
