package VisitorPattern.Expressions;

import VisitorPattern.Node;
import VisitorPattern.Visitor;

public class Identifier extends Node implements Exp{
    public String attrib;

    public Identifier(String attrib) {
        this.attrib = attrib;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "attrib='" + attrib + '\'' +
                '}';
    }

    public Object accept(Visitor v ){
        return v.visit(this);
    }
}
