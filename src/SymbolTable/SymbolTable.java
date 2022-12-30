package SymbolTable;

import VisitorPattern.Expressions.Identifier;

import java.util.ArrayList;

public class SymbolTable {
    public String nameTable;
    public ArrayList<NewLangSymbol> symbols;
    public SymbolTable typeEnvironment;


    public SymbolTable(String nt, SymbolTable prec){
        typeEnvironment = prec;
        symbols = new ArrayList<>();
        nameTable = nt;
    }

    public void addSymbol(NewLangSymbol symbol) {
        symbols.add(symbol);
    }

    public boolean findSymbol(String symbol){
        for (NewLangSymbol sym : symbols){
            if (sym.getSymbol().equals(symbol)){
                return true;
            }
        }
        return false;
    }

    public boolean isSymbolDuplication(String identifier){
        for (NewLangSymbol sym : symbols){
            if (sym.getSymbol().compareTo(identifier) == 0){
                return true;
            }
        }
        return false;
    }

    public static String lookup(SymbolTable symbols, String symbol){
        if(symbols == null)
            return null;

        for(NewLangSymbol sym : symbols.symbols)
            if(sym.getSymbol().equals(symbol))
                return sym.getType();

        return lookup(symbols.typeEnvironment, symbol);
    }
}
