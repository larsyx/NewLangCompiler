package VisitorPattern;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.IdInit;
import VisitorPattern.Program.IdInit.IdInitList;
import VisitorPattern.Program.IdInit.IdInitObblList;
import VisitorPattern.Stat.*;

public interface Visitor {

    //Program
    Object visit(BodyOp e) throws SemanticErrorException;;

    Object visit(FunOp e) throws SemanticErrorException;;

    Object visit(ParDeclOp e) throws SemanticErrorException;;

    Object visit(ProgramOp e) throws SemanticErrorException;;

    Object visit(DeclList e) throws SemanticErrorException;;
    Object visit(VarDeclOp e) throws SemanticErrorException;;


    //Statement
    Object visit(AssignOp e);

    Object visit(ForOp e) throws SemanticErrorException;

    Object visit(FunCallOp e);

    Object visit(IfOp e) throws SemanticErrorException;

    Object visit(ReadOp e);

    Object visit(ReturnOp e);

    Object visit(WhileOp e) throws SemanticErrorException;

    Object visit(WriteOp e);

    //Expressions
    Object visit(AddOp e);

    Object visit(AndOp e);

    Object visit(Char_const e);

    Object visit(DiffOp e);

    Object visit(DivIntOp e);

    Object visit(DivOp e);

    Object visit(EQOp e);

    Object visit(False_const e);

    Object visit(GEOp e);

    Object visit(GTOp e);

    Object visit(Identifier e);

    Object visit(Integer_const e);

    Object visit(LEOp e);

    Object visit(LTOp e);

    Object visit(MulOp e);

    Object visit(NEOp e);

    Object visit(NotOp e);

    Object visit(OrOp e);

    Object visit(PowOp e);

    Object visit(Real_const e);

    Object visit(StrCatOp e);

    Object visit(String_const e);

    Object visit(True_const e);

    Object visit(UminusOp e);

    // da implementare
    Object visit(ParamDeclList e) throws SemanticErrorException;
    Object visit(VarDeclList e) throws SemanticErrorException;

    Object visit(IdInit e);
    Object visit(IdInitList e);
    Object visit(IdInitObblList e);

    Object visit(StatementList e) throws SemanticErrorException;

    Object visit(IdentifierList e);
    Object visit(ExpressionList e);

}
