package SymbolTable;

import SymbolTable.*;
import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.*;
import VisitorPattern.Stat.*;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class CreateSymbolTable implements Visitor {

    public ArrayList<SymbolTable> tables;
    public SymbolTable current;
    public ArrayList<FunCallOp> funCall = new ArrayList<>();

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
            id.setType_node(e.type);
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

        checkFunCall();

        return st;
    }

    private void checkFunCall() throws SemanticErrorException {
        for( FunCallOp in : funCall){
            if(!current.findSymbol(in.id.attrib))
                throw new SemanticErrorException("Non e stata dichiarata nessuna funzione: " + in.id.attrib);
        }
    }

    public Object visit(DeclList e) throws SemanticErrorException {

        VarDeclList newOp = new VarDeclList();
        ArrayList<VarDeclOp> remove = new ArrayList<>();
        for(VarDeclOp op: e.varDeclList) {
            if(op.isVar) {
                remove.add(op);
                newOp = (VarDeclList) op.accept(this);
            }
            else
                op.accept(this);
        }

        for(VarDeclOp rem : remove)
            e.varDeclList.remove(rem);
        for(VarDeclOp vp: newOp.varDeclOps)
            e.varDeclList.add(vp);

        for(FunOp o : e.funDeclList)
            o.accept(this);

        return null;
    }
    public Object visit(VarDeclOp e) throws SemanticErrorException {

        NewLangSymbol symbol = null;
        if(!e.isVar){
            IdInitList idlist = (IdInitList) e.idList;
            for (IdInit id : idlist.idInits) {
                symbol = new NewLangSymbol(id.id.attrib, "var", e.type);
                if (current.isSymbolDuplication(symbol.getSymbol()))
                    throw new SemanticErrorException("Errore dichiarazione multipla di: " + symbol.getSymbol());
                current.addSymbol(symbol);
                id.id.setType_node(e.type);                            //asegnazione type per type checking
                if(id.assign !=null)
                    id.assign.accept(this);
            }
        } else {
            VarDeclList varDeclList = new VarDeclList();            //gestione inferenza var
            IdInitList newList = new IdInitList();
            String type;
            IdInitObblList idlist = (IdInitObblList) e.idList;
            for (IdInit id : idlist.idInits) {
                newList.addIdInit(new IdInit(id.id, (Exp) id.assignConst));
                type = (String) id.assignConst.accept(this);
                varDeclList.addVarDeclOp(new VarDeclOp(type,newList));
                newList = new IdInitList();

                symbol = new NewLangSymbol(id.id.attrib, "var", type);
                if (current.isSymbolDuplication(symbol.getSymbol()))
                    throw new SemanticErrorException("Errore dichiarazione multipla di: " + symbol.getSymbol());
                current.addSymbol(symbol);
                id.id.setType_node(type);                           //asegnazione type per type checking
            }
            e.setVar(false);
            e.idList=newList;

            return varDeclList;
        }


        return current;
    }


    //Statement
    public Object visit(AssignOp e) throws SemanticErrorException {

        e.idList.accept(this);
        e.exprList.accept(this);

        return current;
    }

    public Object visit(ForOp e) throws SemanticErrorException {
        e.intConst.accept(this);
        e.toIntConst.accept(this);

        SymbolTable symbolTable = new SymbolTable("forOp", current);
        tables.add(symbolTable);
        current = symbolTable;

        symbolTable.addSymbol(new NewLangSymbol(e.id.attrib, "var", "integer"));

        e.bodyOp.accept(this);

        current = current.typeEnvironment;
        return current.typeEnvironment;
    }

    public Object visit(FunCallOp e) throws SemanticErrorException {
        if(e.exprList != null)
            e.exprList.accept(this);
        funCall.add(e);

        return current;
    }

    public Object visit(IfOp e) throws SemanticErrorException {

        e.expression.accept(this);

        SymbolTable symbolTable = new SymbolTable("IfOp", current);
        tables.add(symbolTable);

        current = symbolTable;

        e.body.accept(this);
        current = current.typeEnvironment;

        if(e.elseBody !=null) {
            symbolTable = new SymbolTable("IfOpElse", current);
            tables.add(symbolTable);
            current = symbolTable;

            e.elseBody.accept(this);


            current = current.typeEnvironment;
        }

        return current.typeEnvironment;
    }

    public Object visit(ReadOp e) throws SemanticErrorException {
        if(e.string_const!=null)
            e.string_const.accept(this);
        e.idList.accept(this);
        return current;
    }

    public Object visit(ReturnOp e) throws SemanticErrorException {
        if(e.expression != null)
            e.expression.accept(this);
        return current;
    }

    public Object visit(WhileOp e) throws SemanticErrorException {

        e.expression.accept(this);

        SymbolTable symbolTable = new SymbolTable("WhileOp", current);
        tables.add(symbolTable);
        current = symbolTable;

        // e.expression.accept(this);

        e.bodyOp.accept(this);

        current = current.typeEnvironment;
        return current.typeEnvironment;
    }

    public Object visit(WriteOp e) throws SemanticErrorException {

        e.exprList.accept(this);

        return current;
     }

    //Expressions
    public Object visit(AddOp e) throws SemanticErrorException {
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(AndOp e) throws SemanticErrorException {
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(Char_const e) {

        e.setType_node("char");
        return "char";
    }

    public Object visit(DiffOp e) throws SemanticErrorException {
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(DivOp e)throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(EQOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(False_const e) {

        e.setType_node("boolean");
        return "boolean";
    }

    public Object visit(GEOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(GTOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(Identifier e) throws SemanticErrorException {

        String type_node = "";
        type_node = SymbolTable.lookup(current, e.attrib);
        if(type_node == null)
            throw new SemanticErrorException("Variabile non dichiarata: " + e.attrib);

        e.setType_node(type_node);
        return current;
    }

    public Object visit(Integer_const e) {
        e.setType_node("integer");
        return "integer";
    }

    public Object visit(LEOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(LTOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(MulOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(NEOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(NotOp e) throws SemanticErrorException{
        e.exp.accept(this);
        return current;
    }

    public Object visit(OrOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(PowOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(Real_const e) {
        e.setType_node("float");
        return "float";
    }

    public Object visit(StrCatOp e) throws SemanticErrorException{
        e.left.accept(this);
        e.right.accept(this);
        return current;
    }

    public Object visit(String_const e) {

        e.setType_node("string");
        return "string";
    }

    public Object visit(True_const e) {

        e.setType_node("boolean");
        return "boolean";
    }

    public Object visit(UminusOp e) throws SemanticErrorException{
        e.exp.accept(this);
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

        VarDeclList newOp = new VarDeclList();
        ArrayList<VarDeclOp> remove = new ArrayList<>();
        for(VarDeclOp op: e.varDeclOps) {
            if(op.isVar) {
                remove.add(op);
                VarDeclList temp = (VarDeclList) op.accept(this);
                for(VarDeclOp op1: temp.varDeclOps)
                    newOp.addVarDeclOp(op1);
            }
            else
                op.accept(this);

        }

        for(VarDeclOp rem : remove)
            e.removeVarDeclOp(rem);
        for(VarDeclOp vp: newOp.varDeclOps)
            e.addVarDeclOp(vp);

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
    public Object visit(IdentifierList e) throws SemanticErrorException {
        for(Identifier i: e.ids)
            if(i != null)
                i.accept(this);
        return current;
    }

    @Override
    public Object visit(ExpressionList e) throws SemanticErrorException {

        for( Exp exp: e.expList)
            exp.accept(this);

        return current;
    }

    @Override
    public Object visit(SwitchStatOp e) throws SemanticErrorException {
        e.id.accept(this);
        e.c1.accept(this);
        e.st1.accept(this);
        e.c2.accept(this);
        e.st2.accept(this);
        e.c3.accept(this);
        e.st3.accept(this);

        return current;
    }

}
