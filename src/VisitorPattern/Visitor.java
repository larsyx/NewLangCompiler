package VisitorPattern;

import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.IdInit;
import VisitorPattern.Program.IdInit.IdInitList;
import VisitorPattern.Program.IdInit.IdInitObblList;
import VisitorPattern.Stat.*;

public interface Visitor {

    //Program
    Object visit(BodyOp e);;

    Object visit(FunOp e);;

    Object visit(ParDeclOp e);;

    Object visit(ProgramOp e);;

    Object visit(DeclList e);;
    Object visit(VarDeclOp e);;


    //Statement
    Object visit(AssignOp e);

    Object visit(ForOp e);

    Object visit(FunCallOp e);

    Object visit(IfOp e);

    Object visit(ReadOp e);

    Object visit(ReturnOp e);

    Object visit(WhileOp e);

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
    Object visit(ParamDeclList e);
    Object visit(VarDeclList e);

    Object visit(IdInit e);
    Object visit(IdInitList e);
    Object visit(IdInitObblList e);

    Object visit(StatementList e);

    Object visit(IdentifierList e);
    Object visit(ExpressionList e);

}
