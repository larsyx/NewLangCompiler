package SymbolTable;

import VisitorPattern.Expressions.Identifier;

public class NewLangSymbol {
    private String symbol;
    private String kind;
    private String type;
    private String proprieries;

    public NewLangSymbol(String identifier, String kind, String type, String proprieries) {
        this.symbol = identifier;
        this.kind = kind;
        this.type = type;
        this.proprieries = proprieries;
    }

    public NewLangSymbol(String identifier, String kind, String type) {
        this.symbol = identifier;
        this.kind = kind;
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProprieries() {
        return proprieries;
    }

    public void setProprieries(String proprieries) {
        this.proprieries = proprieries;
    }
}
