package VisitorPattern.Expressions.Const;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

public interface Const {
    public Object accept(Visitor v );
}
