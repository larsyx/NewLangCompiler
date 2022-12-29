package VisitorPattern;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.IdInit;
import VisitorPattern.Program.IdInit.IdInitList;
import VisitorPattern.Program.IdInit.IdInitObblList;
import VisitorPattern.Stat.*;

public class PrintSintaxTree implements Visitor{


    //Program
    public Object visit(BodyOp e) throws SemanticErrorException {
        String str ="<BodyOp> \n";

        str += e.varDeclList.accept(this);
        str += e.statList.accept(this);

        str += "\n</BodyOp> \n";
        return str;
    }

    public Object visit(FunOp e) throws SemanticErrorException {
        String str ="<FunOp> \n";

        str+= e.id.accept(this);
        if(e.paramDeclList != null)
            str += e.paramDeclList.accept(this);
        str += "<Type> \n" + e.type + "\n</Type> \n";
        str += e.body.accept(this);

        str +="\n</FunOp> \n";
        return str;
    }

    public Object visit(ParDeclOp e) throws SemanticErrorException {
        String str ="<ParDeclOp> \n";

        str += "<InOrOut> \n" + e.outOrIn + "\n</InOrOut> \n";
        str += "<Type> \n" + e.type + "\n</Type> \n";
        str += e.id.accept(this);

        str +="\n</ParDeclOp> \n";
        return str;
    }

    public Object visit(ProgramOp e) throws SemanticErrorException {
        String str ="<ProgramOp> \n";

        str += e.declList_f.accept( this);
        str += e.main.accept( this);
        str += e.declList_s.accept(this);

        str +="\n</ProgramOp> \n";
        return str;
    }

    public Object visit(DeclList e) throws SemanticErrorException {
        String str= "<DeclList>\n";

        for(VarDeclOp o : e.varDeclList)
            str += o.accept(this);

        for(FunOp o : e.funDeclList)
            str += o.accept(this);

        str += "\n</DeclList>\n";
        return str;
    }
    public Object visit(VarDeclOp e) throws SemanticErrorException {
        String str ="<VarDeclOp> \n";

        if(e.type!=null)
            str += "<Type> \n" + e.type + "\n</Type> ";
        else
            str += "<VAR></VAR>";
        str += e.idList.accept(this);

        str +="\n</VarDeclOp> \n";
        return str;
    }

    //Statement
    public Object visit(AssignOp e) throws SemanticErrorException {
        String str ="<AssignOp> \n";

        str += e.idList.accept(this);
        str += e.exprList.accept(this);

        str +="\n</AssignOp> \n";
        return str;
    }

    public Object visit(ForOp e) throws SemanticErrorException {
        String str ="<ForOp> \n";

        str += e.id.accept(this);
        str += e.intConst.accept(this);
        str += e.toIntConst.accept(this);
        str += e.bodyOp.accept(this);

        str +="\n</ForOp> \n";
        return str;
    }

    public Object visit(FunCallOp e) throws SemanticErrorException {
        String str ="<FunCallOp> \n";

        str += e.id.accept(this);
        if(e.exprList != null)
            str += e.exprList.accept(this);

        str +="\n</FunCallOp> \n";
        return str;
    }

    public Object visit(IfOp e) throws SemanticErrorException {
        String str ="<IfOp> \n";

        str += e.expression.accept(this);
        str += e.body.accept(this);
        if(e.elseBody !=null)
            str += e.elseBody.accept(this);

        str +="\n</IfOp> \n";
        return str;
    }

    public Object visit(ReadOp e) throws SemanticErrorException {
        String str ="<ReadOp> \n";

        for(Identifier i: e.idList.ids)
            str += i.accept(this);

        str +="\n</ReadOp> \n";
        return str;
    }

    public Object visit(ReturnOp e) throws SemanticErrorException {
        String str ="<ReturnOp> \n";

        str += e.expression.accept(this);

        str +="\n</ReturnOp> \n";
        return str;
    }

    public Object visit(WhileOp e) throws SemanticErrorException {
        String str ="<WhileOp> \n";

        str += e.expression.accept(this);
        str += e.bodyOp.accept(this);

        str +="\n</WhileOp> \n";
        return str;
    }

    public Object visit(WriteOp e) throws SemanticErrorException {
        String str ="<WriteOp> \n";

        str += "<Writeln> "+ e.newLine + " </Writeln> \n";
        str += e.exprList.accept(this);

        str +="</WriteOp> \n";
        return str;
    }

    //Expressions
    public Object visit(AddOp e) throws SemanticErrorException {
        String str ="<AddOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</AddOp> \n";
        return str;
    }

    public Object visit(AndOp e) throws SemanticErrorException {
        String str ="<AndOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</AndOp> \n";
        return str;
    }

    public Object visit(Char_const e) {
        String str ="<Char_const> \n";

        str += e.attrib;

        str +="\n</Char_const> \n";
        return str;
    }

    public Object visit(DiffOp e) throws SemanticErrorException {
        String str ="<DiffOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</DiffOp> \n";
        return str;
    }

    public Object visit(DivIntOp e) throws SemanticErrorException {
        String str ="<DivIntOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</DivIntOp> \n";
        return str;
    }

    public Object visit(DivOp e) throws SemanticErrorException {
        String str ="<DivOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</DivOp> \n";
        return str;
    }

    public Object visit(EQOp e) throws SemanticErrorException {
        String str ="<EQOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</EQOp> \n";
        return str;
    }

    public Object visit(False_const e) {
        String str ="<Identifier> \n";

        str += "" + e.false_const;

        str +="\n</Identifier> \n";
        return str;
    }

    public Object visit(GEOp e) throws SemanticErrorException {
        String str ="<GEOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</GEOp> \n";
        return str;
    }

    public Object visit(GTOp e) throws SemanticErrorException {
        String str ="<GTOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</GTOp> \n";
        return str;
    }

    public Object visit(Identifier e) {
        String str ="<Identifier> \n";

        str += e.attrib;

        str +="\n</Identifier> \n";
        return str;
    }

    public Object visit(Integer_const e) {
        String str ="<Integer_const> \n";

        str += e.attrib;

        str +="\n</Integer_const> \n";
        return str;
    }

    public Object visit(LEOp e) throws SemanticErrorException {
        String str ="<LEOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</LEOp> \n";
        return str;
    }

    public Object visit(LTOp e) throws SemanticErrorException {
        String str ="<LTOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</LTOp> \n";
        return str;
    }

    public Object visit(MulOp e) throws SemanticErrorException {
        String str ="<MulOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</MulOp> \n";
        return str;
    }

    public Object visit(NEOp e) throws SemanticErrorException {
        String str ="<NEOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</NEOp> \n";
        return str;
    }

    public Object visit(NotOp e) throws SemanticErrorException {
        String str ="<NotOp> \n";

        str += e.exp.accept(this);

        str +="\n</NotOp> \n";
        return str;
    }

    public Object visit(OrOp e) throws SemanticErrorException {
        String str ="<OrOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</OrOp> \n";
        return str;
    }

    public Object visit(PowOp e) throws SemanticErrorException {
        String str ="<PowOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</PowOp> \n";
        return str;
    }

    public Object visit(Real_const e) {
        String str ="<Real_const> \n";

        str += "" + e.attrib;

        str +="\n</Real_const> \n";
        return str;
    }

    public Object visit(StrCatOp e) throws SemanticErrorException {
        String str ="<StrCatOp> \n";

        str += e.left.accept(this);
        str += e.right.accept(this);

        str +="\n</StrCatOp> \n";
        return str;
    }

    public Object visit(String_const e) {
        String str ="<String_const> \n";

        str += e.attrib;

        str +="\n</String_const> \n";
        return str;
    }

    public Object visit(True_const e) {
        String str ="<True_const> \n";

        str += "" + e.true_const;

        str +="\n</True_const> \n";
        return str;
    }

    public Object visit(UminusOp e) throws SemanticErrorException {
        String str ="<UminusOp> \n";

        str += e.exp.accept(this);

        str +="\n</UminusOp> \n";
        return str;
    }

    @Override
    public Object visit(ParamDeclList e) throws SemanticErrorException {
        String str ="<ParamDeclList> \n";

        for(ParDeclOp op: e.parDeclOps)
            str += op.accept(this);

        str += "\n</ParamDeclList> \n";
        return str;
    }

    @Override
    public Object visit(VarDeclList e) throws SemanticErrorException {
        String str ="<VarDeclList> \n";

        for(VarDeclOp op: e.varDeclOps)
            str += op.accept(this);

        str += "\n</VarDeclList> \n";
        return str;
    }

    @Override
    public Object visit(IdInit e) throws SemanticErrorException {
        String str ="<IdInit> \n";

        str += e.id.accept(this);
        if(e.assign != null)
            str += e.assign.accept(this);
        if(e.assignConst !=null)
            str += e.assignConst.accept(this);

        str += "</IdInit> \n";
        return str;
    }

    @Override
    public Object visit(IdInitList e) throws SemanticErrorException {
        String str ="\n<IdInitList> \n";

        for(IdInit idInit: e.idInits)
            str += idInit.accept(this);

        str += "\n</IdInitList> \n";
        return str;
    }

    @Override
    public Object visit(IdInitObblList e) throws SemanticErrorException {
        String str ="<IdInitObblList> \n";

        for(IdInit idInit: e.idInits)
            str += idInit.accept(this);

        str += "\n</IdInitObblList> \n";
        return str;
    }

    @Override
    public Object visit(StatementList e) throws SemanticErrorException {
        String str ="<StmtList> \n";

        for(StatOp stmt: e.statList)
            if(stmt != null)
                str += stmt.accept(this);

        str += "\n</StmtList> \n";
        return str;
    }

    @Override
    public Object visit(IdentifierList e) throws SemanticErrorException {
        String str ="<IdList> \n";

        for(Identifier id: e.ids)
            str += id.accept(this);

        str += "\n</IdList> \n";
        return str;
    }

    @Override
    public Object visit(ExpressionList e) throws SemanticErrorException {
        String str ="<ExpressionList> \n";

        for(Exp exp: e.expList)
            str += exp.accept(this);

        str += "</ExpressionList> \n";
        return str;
    }

}
