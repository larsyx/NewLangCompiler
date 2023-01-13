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
    Object visit(AssignOp e) throws SemanticErrorException;

    Object visit(ForOp e) throws SemanticErrorException;

    Object visit(FunCallOp e) throws SemanticErrorException;

    Object visit(IfOp e) throws SemanticErrorException;

    Object visit(ReadOp e) throws SemanticErrorException;

    Object visit(ReturnOp e) throws SemanticErrorException;

    Object visit(WhileOp e) throws SemanticErrorException;

    Object visit(WriteOp e) throws SemanticErrorException;

    //Expressions
    Object visit(AddOp e) throws SemanticErrorException;

    Object visit(AndOp e) throws SemanticErrorException;

    Object visit(Char_const e);

    Object visit(DiffOp e) throws SemanticErrorException;

    Object visit(DivOp e) throws SemanticErrorException;

    Object visit(EQOp e) throws SemanticErrorException;

    Object visit(False_const e);

    Object visit(GEOp e) throws SemanticErrorException;

    Object visit(GTOp e) throws SemanticErrorException;

    Object visit(Identifier e) throws SemanticErrorException;

    Object visit(Integer_const e);

    Object visit(LEOp e) throws SemanticErrorException;

    Object visit(LTOp e) throws SemanticErrorException;

    Object visit(MulOp e) throws SemanticErrorException;

    Object visit(NEOp e) throws SemanticErrorException;

    Object visit(NotOp e) throws SemanticErrorException;

    Object visit(OrOp e) throws SemanticErrorException;

    Object visit(PowOp e) throws SemanticErrorException;

    Object visit(Real_const e);

    Object visit(StrCatOp e) throws SemanticErrorException;

    Object visit(String_const e);

    Object visit(True_const e);

    Object visit(UminusOp e) throws SemanticErrorException;


    Object visit(ParamDeclList e) throws SemanticErrorException;
    Object visit(VarDeclList e) throws SemanticErrorException;

    Object visit(IdInit e) throws SemanticErrorException;
    Object visit(IdInitList e) throws SemanticErrorException;
    Object visit(IdInitObblList e) throws SemanticErrorException;

    Object visit(StatementList e) throws SemanticErrorException;

    Object visit(IdentifierList e) throws SemanticErrorException;
    Object visit(ExpressionList e) throws SemanticErrorException;

}
