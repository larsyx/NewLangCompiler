package TypeChecking;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Node;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.IdInit;
import VisitorPattern.Program.IdInit.IdInitList;
import VisitorPattern.Program.IdInit.IdInitObblList;
import VisitorPattern.Program.IdInit.InitList;
import VisitorPattern.Stat.*;
import VisitorPattern.Visitor;

import java.util.ArrayList;
import java.util.HashMap;


public class TypeChecking implements Visitor {

    public static final String NOTYPE = "NOTYPE";
    public static final String ERROR = "error";
    public static final String INTEGER = "integer";
    public static final String BOOLEAN = "boolean";
    public static final String FLOAT = "float";
    public static final String CHAR= "char";
    public static final String STRING = "string";

    public String resultType;
    public HashMap<String,FunOp> funOps;
    public ArrayList<FunCallOp> funCallOps;

    public TypeChecking() {
        funOps = new HashMap<>();
        funCallOps = new ArrayList<>();
    }

    @Override
    public Object visit(BodyOp e) throws SemanticErrorException {

        if(e.varDeclList.accept(this).equals(NOTYPE) && e.statList.accept(this).equals(NOTYPE)){
            e.setType_node(NOTYPE);
            return NOTYPE;
        }

        e.setType_node(ERROR);
        throw new SemanticErrorException("Errore Body OP");
    }

    @Override
    public Object visit(FunOp e) throws SemanticErrorException {

        if(e.paramDeclList != null)
            e.paramDeclList.accept(this);

        e.body.accept(this);

        if(resultType == null) {
            e.setType_node(ERROR);
            throw new SemanticErrorException("return non presente");
        }
        if(!resultType.equals(e.type)){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Tipo di ritorno non corrisponde con firma della funzione");
        }

        resultType = null;
        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(ParDeclOp e) throws SemanticErrorException {

        String type = (String) e.id.ids.get(0).accept(this);
        for(Identifier i: e.id.ids)
            if (!i.accept(this).equals(type)){
                e.setType_node(ERROR);
                throw new SemanticErrorException("ParDeclOp tipi non uguali");
            }

        e.setType_node(type);
        return type;
    }

    @Override
    public Object visit(ProgramOp e) throws SemanticErrorException {
        saveFunOp(e);

        if(e.declList_f.accept(this).equals(NOTYPE) && e.main.accept(this).equals(NOTYPE) && e.declList_s.accept(this).equals(NOTYPE)){
            e.setType_node(NOTYPE);
            return NOTYPE;
        }

        e.setType_node(ERROR);
        throw new SemanticErrorException("Errore Body OP");
    }

    @Override
    public Object visit(DeclList e) throws SemanticErrorException {
        for(VarDeclOp vdl : e.varDeclList) {
            if (vdl.accept(this).equals(ERROR)) {
                e.setType_node(ERROR);
                throw new SemanticErrorException("errore varDeclList");
            }
        }

        for(FunOp fo : e.funDeclList) {
            if (fo.accept(this).equals(ERROR)) {
                e.setType_node(ERROR);
                throw new SemanticErrorException("errore funDeclList");
            }
        }

        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(VarDeclOp e) throws SemanticErrorException {
        if(e.type.equals(e.idList.accept(this))){
            e.setType_node(NOTYPE);
            return NOTYPE;
        }

        e.setType_node(ERROR);
        throw new SemanticErrorException("errore dichiarazione variabili ");
    }

    //statement
    @Override
    public Object visit(AssignOp e) throws SemanticErrorException {
        //controllo numero
        if(e.idList.ids.size() != e.exprList.expList.size()) {
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore durante l'assegnazione delle variabili");
        }
        //controllo corrispondenza i-esima assegnazione
        Identifier id = e.idList.ids.get(0);
        Exp exp = e.exprList.expList.get(0);
        for(int i = 0 ; i < e.idList.ids.size(); id = e.idList.ids.get(i), exp = e.exprList.expList.get(i), i++)
            if(!id.accept(this).equals(exp.accept(this))) {
                e.setType_node(ERROR);
                throw new SemanticErrorException("Errore assegnazione: " + id.attrib + " e " + exp.accept(this));
            }

        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(ForOp e) throws SemanticErrorException {

        if(!(e.intConst.accept(this).equals(INTEGER) && e.toIntConst.accept(this).equals(INTEGER))){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore For paramentri non interi");
        }

        if(!e.bodyOp.accept(this).equals(NOTYPE)){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore Body For");
        }
        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(FunCallOp e) throws SemanticErrorException {

        funCallOps.add(e);

        if(e.exprList != null)
            e.exprList.accept(this);

        return funOps.get(e.id.attrib).type;              //da implementare
    }

    @Override
    public Object visit(IfOp e) throws SemanticErrorException {
        if(!e.expression.accept(this).equals(NOTYPE)){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore Body For");
        }

        if(!e.body.accept(this).equals(NOTYPE)) {
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore Body If");
        }
        if(e.elseBody!= null && !(e.elseBody.accept(this).equals(NOTYPE))){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore ElseBody If");
        }
        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(ReadOp e) throws SemanticErrorException {

        if(!(e.idList.accept(this).equals(NOTYPE)))
            e.setType_node(ERROR);

        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(ReturnOp e) throws SemanticErrorException {
        if(e.expression == null)
            resultType = "void";
        else
            resultType = (String) e.expression.accept(this);

        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(WhileOp e) throws SemanticErrorException {
        if(!e.expression.equals(BOOLEAN)){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore espressione while non boolean");
        }
        if(!e.bodyOp.accept(this).equals(NOTYPE)){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore Body while");
        }

        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(WriteOp e) throws SemanticErrorException {
        if(!e.exprList.accept(this).equals(NOTYPE)){
            e.setType_node(ERROR);
            throw new SemanticErrorException("Errore writeOp");
        }
        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    //Expression
    @Override
    public Object visit(AddOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.PLUS, left, right);
    }

    @Override
    public Object visit(AndOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.AND, left, right);
    }

    @Override
    public Object visit(Char_const e) {
        return e.getType_node();
    }

    @Override
    public Object visit(DiffOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.MINUS, left, right);
    }

    @Override
    public Object visit(DivIntOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.DIV, left, right);   //controllare
    }

    @Override
    public Object visit(DivOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.DIV, left, right);
    }

    @Override
    public Object visit(EQOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.EQ, left, right);
    }

    @Override
    public Object visit(False_const e) {
        return e.getType_node();
    }

    @Override
    public Object visit(GEOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.GE, left, right);
    }

    @Override
    public Object visit(GTOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.GT, left, right);
    }

    @Override
    public Object visit(Identifier e) throws SemanticErrorException {
        return e.getType_node();
    }

    @Override
    public Object visit(Integer_const e) {
        return e.getType_node();
    }

    @Override
    public Object visit(LEOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.LE, left, right);
    }

    @Override
    public Object visit(LTOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.LT, left, right);
    }

    @Override
    public Object visit(MulOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.TIMES, left, right);
    }

    @Override
    public Object visit(NEOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.NE, left, right);
    }

    @Override
    public Object visit(NotOp e) throws SemanticErrorException {
        String exp = (String) e.exp.accept(this);

        return OperatorsTables.opt(OperatorsTables.NOT, exp);
    }

    @Override
    public Object visit(OrOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.OR, left, right);
    }

    @Override
    public Object visit(PowOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.POW, left, right);
    }

    @Override
    public Object visit(Real_const e) {
        return e.getType_node();
    }

    @Override
    public Object visit(StrCatOp e) throws SemanticErrorException {
        String left = (String) e.left.accept(this);
        String right = (String) e.right.accept(this);

        return OperatorsTables.opt(OperatorsTables.STR_CONCAT, left, right);
    }

    @Override
    public Object visit(String_const e) {
        return e.getType_node();
    }

    @Override
    public Object visit(True_const e) {
        return e.getType_node();
    }

    @Override
    public Object visit(UminusOp e) throws SemanticErrorException {
        String exp = (String) e.exp.accept(this);

        return OperatorsTables.opt(OperatorsTables.MINUS, exp);
    }

    @Override
    public Object visit(ParamDeclList e) throws SemanticErrorException {

        for(ParDeclOp op : e.parDeclOps)
            op.accept(this);
        return NOTYPE;
    }

    @Override
    public Object visit(VarDeclList e) throws SemanticErrorException {
        for (VarDeclOp op : e.varDeclOps){
            if(!op.accept(this).equals(NOTYPE)){
                e.setType_node(ERROR);
                throw new SemanticErrorException("errore VarDeclList");
            }
        }
        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(IdInit e) throws SemanticErrorException {
        return e.id.accept(this);
    }

    @Override
    public Object visit(IdInitList e) throws SemanticErrorException {
        String result = (String) e.idInits.get(0).accept(this);
        for(IdInit idInit: e.idInits)      //solo idInitList perchè l'inferenza è stata gestita nella creazione della tabella dei simboli
            if(!result.equals(idInit.accept(this))){
                e.setType_node(ERROR);
                throw new SemanticErrorException("errore inizializazione variabili");
            }

        e.setType_node(result);
        return result;
    }

    @Override
    public Object visit(IdInitObblList e) throws SemanticErrorException {
        return NOTYPE;
    }

    @Override
    public Object visit(StatementList e) throws SemanticErrorException {

        for(StatOp op : e.statList) {
            if(op!=null)
                if (!op.accept(this).equals(NOTYPE)) {
                   e.setType_node(ERROR);
                   throw new SemanticErrorException("errore statementlist");
                }
        }
        e.setType_node(NOTYPE);
        return NOTYPE;

    }

    @Override
    public Object visit(IdentifierList e) throws SemanticErrorException {
        for(Identifier i : e.ids)
            i.accept(this);
        e.setType_node(NOTYPE);
        return NOTYPE;
    }

    @Override
    public Object visit(ExpressionList e) throws SemanticErrorException {
        for(Exp exp : e.expList){
            exp.accept(this);
        }
        e.setType_node(NOTYPE);
        return NOTYPE;
    }



    public void checkFunCallParam() throws SemanticErrorException {
        for(FunCallOp op : funCallOps){
            FunOp fun = funOps.get(op.id.attrib);
            if(fun.paramDeclList == null ) {
                if(op.exprList != null)
                    throw new SemanticErrorException("errore paramentri chiamata funzione");
            }
            else if(op.exprList == null ) {
                throw new SemanticErrorException("errore paramentri chiamata funzione");
            }
            else if(fun.paramDeclList.parDeclOps.size() == op.exprList.expList.size()) {
                ParDeclOp declop = fun.paramDeclList.parDeclOps.get(0);
                Exp exp = op.exprList.expList.get(0);
                for (int i = 0; i < fun.paramDeclList.parDeclOps.size(); declop = fun.paramDeclList.parDeclOps.get(i), exp = op.exprList.expList.get(i), i++)
                    if (!declop.accept(this).equals(exp.accept(this)))
                        throw new SemanticErrorException("errore parametri chiamata funzione");

            }
        }
    }

    private void saveFunOp(ProgramOp op){
        funOps.put(op.main.type,op.main);
        if(op.declList_f.funDeclList!=null)
            for(FunOp fun: op.declList_f.funDeclList)
                funOps.put(fun.id.attrib,fun);
        if(op.declList_s.funDeclList!=null)
            for(FunOp fun: op.declList_s.funDeclList)
                funOps.put(fun.id.attrib,fun);
    }
}
