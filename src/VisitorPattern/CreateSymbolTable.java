package VisitorPattern;

import SymbolTable.*;
import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.*;
import VisitorPattern.Stat.*;

import java.util.ArrayList;

public class CreateSymbolTable implements Visitor{

    public ArrayList<SymbolTable> tables;
    public SymbolTable current;

    public CreateSymbolTable() {
        this.tables = new ArrayList<>();
    }

    public ArrayList<SymbolTable> getTables() {
        return tables;
    }

    public Object visit(BodyOp e) throws SemanticErrorException {

        e.varDeclList.accept(this);

        e.statList.accept(this);

        return current;
    }

    public Object visit(FunOp e) throws SemanticErrorException {

        NewLangSymbol sym = new NewLangSymbol(e.id.attrib, "function", "type: " + e.type);
        if(current.isSymbolDuplication(sym.getSymbol()))
            throw new SemanticErrorException("Errore dichiarazione multipla di: " + sym.getSymbol());
        current.addSymbol(sym);
        SymbolTable symbolTable = new SymbolTable(e.id.attrib, current);
        tables.add(symbolTable);
        current = symbolTable;

        if(e.paramDeclList != null)
            e.paramDeclList.accept(this);

        e.body.accept(this);

        current = current.typeEnvironment;
        return current.typeEnvironment;
    }

    public Object visit(ParDeclOp e) throws SemanticErrorException {
        NewLangSymbol symbol = null;
        for(Identifier id : e.id.ids) {
            symbol = new NewLangSymbol(id.attrib, "param", e.type, e.outOrIn);
            if(current.isSymbolDuplication(symbol.getSymbol()))
                throw new SemanticErrorException("Errore dichiarazione multipla di: " + symbol.getSymbol());
            current.addSymbol(symbol);
        }
        return current;
    }

    public Object visit(ProgramOp e) throws SemanticErrorException {

        tables = new ArrayList<>();

        SymbolTable st= new SymbolTable("global", null );
        tables.add(st);
        current = st;


        e.declList_f.accept( this);
        e.main.accept( this);
        e.declList_s.accept(this);

        return st;
    }

    public Object visit(DeclList e) throws SemanticErrorException {

        for(VarDeclOp o : e.varDeclList)
            o.accept(this);

        for(FunOp o : e.funDeclList)
            o.accept(this);

        return null;
    }
    public Object visit(VarDeclOp e) throws SemanticErrorException {

        NewLangSymbol symbol = null;
        if(e.type!=null){
            IdInitList idlist = (IdInitList) e.idList;
            for (IdInit id : idlist.idInits) {
                symbol = new NewLangSymbol(id.id.attrib, "var", e.type);
                if (current.isSymbolDuplication(symbol.getSymbol()))
                    throw new SemanticErrorException("Errore dichiarazione multipla di: " + symbol.getSymbol());
                current.addSymbol(symbol);
            }
        } else {
            IdInitObblList idlist = (IdInitObblList) e.idList;
            for (IdInit id : idlist.idInits) {
                symbol = new NewLangSymbol(id.id.attrib, "var", "var");
                if (current.isSymbolDuplication(symbol.getSymbol()))
                    throw new SemanticErrorException("Errore dichiarazione multipla di: " + symbol.getSymbol());
            }
        }


        return current;
    }

    //Statement
    public Object visit(AssignOp e) {
        return current;
    }

    public Object visit(ForOp e) throws SemanticErrorException {
        current.addSymbol(new NewLangSymbol("forOp", "forOp", "", "from " + e.intConst.attrib + "to " + e.toIntConst.attrib));

        SymbolTable symbolTable = new SymbolTable("forOp", current);
        tables.add(symbolTable);
        current = symbolTable;

        symbolTable.addSymbol(new NewLangSymbol(e.id.attrib, "var", "int"));



        //e.intConst.accept(this);
        //e.toIntConst.accept(this);

        e.bodyOp.accept(this);

        current = current.typeEnvironment;
        return current.typeEnvironment;
    }

    public Object visit(FunCallOp e) {
        return current;
    }

    public Object visit(IfOp e) throws SemanticErrorException {

        current.addSymbol(new NewLangSymbol("IfOp", "IfOp", "", "then"));
        SymbolTable symbolTable = new SymbolTable("IfOp", current);
        tables.add(symbolTable);

        current = symbolTable;

        e.body.accept(this);
        current = current.typeEnvironment;

        if(e.elseBody !=null) {
            current.addSymbol(new NewLangSymbol("IfOp", "IfOp", "", "else"));
            symbolTable = new SymbolTable("IfOpElse", current);
            tables.add(symbolTable);
            current = symbolTable;

            e.elseBody.accept(this);


            current = current.typeEnvironment;
        }

        return current.typeEnvironment;
    }

    public Object visit(ReadOp e) {
        return current;
    }

    public Object visit(ReturnOp e) {
        return current;
    }

    public Object visit(WhileOp e) throws SemanticErrorException {

        current.addSymbol(new NewLangSymbol("WhileOp", "WhileOp", ""));

        SymbolTable symbolTable = new SymbolTable("WhileOp", current);
        tables.add(symbolTable);
        current = symbolTable;

        // e.expression.accept(this);

        e.bodyOp.accept(this);

        current = current.typeEnvironment;
        return current.typeEnvironment;
    }

    public Object visit(WriteOp e) {
        return current;
     }

    //Expressions
    public Object visit(AddOp e) {
        return current;
    }

    public Object visit(AndOp e) {
        return current;
    }

    public Object visit(Char_const e) {
        return current;
    }

    public Object visit(DiffOp e) {
        return current;
    }

    public Object visit(DivIntOp e) {
        return current;
    }

    public Object visit(DivOp e){
        return current;
    }

    public Object visit(EQOp e) {
        return current;
    }

    public Object visit(False_const e) {
        return current;
    }

    public Object visit(GEOp e) {
        return current;
    }

    public Object visit(GTOp e) {
        return current;
    }

    public Object visit(Identifier e) {

        return current;
    }

    public Object visit(Integer_const e) {
        return current;
    }

    public Object visit(LEOp e) {
        return current;
    }

    public Object visit(LTOp e) {
        return current;
    }

    public Object visit(MulOp e) {
        return current;
    }

    public Object visit(NEOp e) {
        return current;
    }

    public Object visit(NotOp e) {
        return current;
    }

    public Object visit(OrOp e) {
        return current;
    }

    public Object visit(PowOp e) {
        return current;
    }

    public Object visit(Real_const e) {
        return current;
    }

    public Object visit(StrCatOp e) {
        return current;
    }

    public Object visit(String_const e) {
        return current;
    }

    public Object visit(True_const e) {
        return current;
    }

    public Object visit(UminusOp e) {
        return current;
    }

    @Override
    public Object visit(ParamDeclList e) throws SemanticErrorException {

        for(ParDeclOp op: e.parDeclOps)
            op.accept(this);

        return current;
    }

    @Override
    public Object visit(VarDeclList e) throws SemanticErrorException {

        for(VarDeclOp op: e.varDeclOps)
            op.accept(this);

        return current;
    }

    @Override
    public Object visit(IdInit e) {
        return current;
    }

    @Override
    public Object visit(IdInitList e) {
        return current;
    }

    @Override
    public Object visit(IdInitObblList e) {
        return current;
    }

    @Override
    public Object visit(StatementList e) throws SemanticErrorException {

        for(StatOp stmt: e.statList)
            if(stmt != null)
                stmt.accept(this);

        return current;
    }

    @Override
    public Object visit(IdentifierList e) {
        return current;
    }

    @Override
    public Object visit(ExpressionList e) {
        return current;
    }

}
