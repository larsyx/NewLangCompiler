package Traduzione;

import SymbolTable.SemanticErrorException;
import VisitorPattern.Expressions.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Node;
import VisitorPattern.Program.*;
import VisitorPattern.Program.IdInit.IdInit;
import VisitorPattern.Program.IdInit.IdInitList;
import VisitorPattern.Program.IdInit.IdInitObblList;
import VisitorPattern.Stat.*;
import VisitorPattern.Visitor;

import java.util.ArrayList;

public class CTranslate implements Visitor {

    private boolean isWriting = false;
    private boolean noSemi = false;
    //Program
    public Object visit(BodyOp e) throws SemanticErrorException {
        String str ="{\n";

        str += e.varDeclList.accept(this);
        str += e.statList.accept(this);

        str += "}\n";
        return str;
    }

    public Object visit(FunOp e) throws SemanticErrorException {
        String str ="\n";

        String tipo = convertiTipi(e.type);
        if(tipo.equals(STRING))
            str += "" + CHAR + "* "; //controllare se prima o dopo
        else
            str += tipo + " ";
        str+= e.id.accept(this);
        str += "(";
        if(e.paramDeclList != null)
            str += e.paramDeclList.accept(this);
        str += ")";
        str += e.body.accept(this);

        str +="\n";
        return str;
    }

    public Object visit(ParDeclOp e) throws SemanticErrorException {
        String str =" ";

        String tipo = convertiTipi(e.type);
        if(e.outOrIn.equals("IN")) {
            if(tipo.equals(STRING)){
                for(Identifier id : e.id.ids) {
                    str += "" + CHAR + "*";
                    str += id.attrib + ",";
                }
                str = str.substring(0,str.length()-1);
                return str;
            }

            for(Identifier id: e.id.ids)
                str += tipo + e.id.accept(this);
        }
        else {
            if(tipo.equals(STRING)) {
                for (Identifier id : e.id.ids) {
                    str += "" + CHAR + "+";
                    str += id.attrib + ",";
                }
                str = str.substring(0, str.length() - 1);
            }
            else{
                for (Identifier id : e.id.ids)
                    str += tipo + "*" + id.attrib + ",";
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public Object visit(ProgramOp e) throws SemanticErrorException {
        String str ="";
        str += "#include <stdio.h>\n";
        str += "#include <string.h>\n";
        str += "#include <stdlib.h>\n";
        str += "#include <math.h>\n";
        str += "#include<stdbool.h>\n";

        str += e.declList_f.accept( this);
        str += e.declList_s.accept(this);

        str += e.main.accept( this);

        str +="\n";
        return str;
    }

    public Object visit(DeclList e) throws SemanticErrorException {
        String str= "\n";

        for(VarDeclOp o : e.varDeclList) {
            String temp = (String) o.accept(this);
            if(temp.substring(temp.length()-1,temp.length()).compareTo(";")!=0)
                str += temp.substring(0, temp.length()-1);
            else
                str += temp;
        }
        for(FunOp o : e.funDeclList)
            str += o.accept(this);

        str += "\n";
        return str;
    }
    public Object visit(VarDeclOp e) throws SemanticErrorException {
        String str ="";


        if(e.type!=null) {
            String tipo = convertiTipi(e.type);
            if(tipo.equals(STRING)){
                str += "" + CHAR + "";
                IdInitList list = (IdInitList) e.idList;
                for (IdInit i : ((IdInitList) e.idList).idInits) {
                    str += " " + i.id.attrib + "[1000] ";
                    if(i.assign!=null)
                        str += "={ " + i.assign.accept(this) + " }";
                    str+=",";
                }
                str = str.substring(0,str.length()-1);
                str +=";";
                return str;
            }

            str += "" + tipo + " ";
            str += e.idList.accept(this);
            str+= ";";
            str +="\n";
            return str;
        }else
            str += "<VAR></VAR>";
        return null;
    }

    //Statement
    public Object visit(AssignOp e) throws SemanticErrorException {
        String str =" ";

        Identifier id = e.idList.ids.get(0);
        Exp ex = e.exprList.expList.get(0);
        if(id.getType_node().equals(STRING)){
            String exString = (String) ex.accept(this);
            if(exString.substring(exString.length()-2, exString.length()-1).equals(";"))
                exString = exString.substring(0,exString.length()-2);
            str += "strcpy(" + id.attrib +"," + exString +") ";
        } else {
            str += id.attrib;
            str += "=";
            str += ex.accept(this);
        }
        if(str.substring(str.length()-1,str.length()).compareTo(";")!=0 && str.substring(str.length()-2,str.length()-1).compareTo(";")!=0 && str.substring(str.length()-3,str.length()-2).compareTo(";")!=0)
            str += ";\n";
        for(int i= 1; i < e.idList.ids.size(); i++, ex = e.exprList.expList.get(i), id = e.idList.ids.get(i)){
            if(id.getType_node().equals(STRING)){
                String exString = (String) ex.accept(this);
                if(exString.substring(exString.length()-2, exString.length()-1).equals(";"))
                    exString = exString.substring(0,exString.length()-1);
                str += "strcpy(" + id.attrib +"," + exString +") ";
            }else {
                str += id.attrib;
                str += "=";
                str += ex.accept(this);
            }
            if(str.substring(str.length()-1,str.length()).compareTo(";")!=0)
                str += ";\n";
        }

        str = str.substring(0, str.length()-1);
        return str;
    }

    public Object visit(ForOp e) throws SemanticErrorException {
        String str ="for(";

        str += " int ";
        String variabile = (String) e.id.accept(this);
        str += variabile;
        str += " = ";
        str += e.intConst.accept(this);
        str += "; "+ variabile +" < ";
        str += e.toIntConst.accept(this);
        str += "; " + variabile +"++)";
        str += e.bodyOp.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(FunCallOp e) throws SemanticErrorException {
        String str =" ";

        str += e.id.accept(this);
        str += "(";

        for(Exp exp: e.exprList.expList)
            str += exp.accept(this) + ",";

        str = str.substring(0, str.length()-1);
        str += "); ";
        return str;
    }

    public Object visit(IfOp e) throws SemanticErrorException {
        String str =" ";
        str += "if( ";
        str += e.expression.accept(this);
        str += " ) ";
        str += e.body.accept(this);
        if(e.elseBody !=null) {
            str +="else ";
            str += e.elseBody.accept(this);
        }
        str +=" ";
        return str;
    }

    public Object visit(ReadOp e) throws SemanticErrorException {
        String str ="";

        str += "scanf(\"";
        for(Identifier i: e.idList.ids) {
            switch (i.getType_node()) {
                case STRING:
                    str += "%s,";
                    break;
                case BOOL:
                case INTEGERNEW:
                    str += "%d,";
                    break;
                case CHAR:
                    str += "%c,";
                    break;
                case FLOAT:
                    str += "%f,";
                    break;
            }
        }
        str = str.substring(0, str.length()-1) + "\"";
        for(Identifier i: e.idList.ids) {
            if(i.getType_node().equals(STRING))
                str += "," + i.attrib;
            else
                str += ",&" + i.attrib;
        }
        str +=");";
        return str;
    }

    public Object visit(ReturnOp e) throws SemanticErrorException {
        String str ="return ";

        if(e.expression !=null)
            str += e.expression.accept(this);

        str +=";";
        return str;
    }

    public Object visit(WhileOp e) throws SemanticErrorException {
        String str =" ";
        str += "while(";
        str += e.expression.accept(this);
        str += ")";
        str += e.bodyOp.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(WriteOp e) throws SemanticErrorException {
        String str =" ";
        isWriting = true;
        str = "printf(\"";
        ArrayList<Integer> esclusione= new ArrayList<>();
        int i=0;
        for( String tipo : (ArrayList<String>) e.exprList.accept(this)){
            //Gestire costanti
            switch (tipo) {
                case CHAR:
                case STRING:
                    str += "%s";
                    break;
                case BOOL:
                case INTEGERNEW:
                    str += "%d";
                    break;
                case FLOAT:
                    str += "%f";
                    break;
                default:
                    str += tipo;
                    esclusione.add(i);
                    break;
            }
            i++;
        }
        if(e.newLine)
            str += "\\n";
        str += "\"";
        isWriting = false;
        i=0;
        for(Exp exp: e.exprList.expList){
            if(!esclusione.contains(i))
                str += "," + exp.accept(this);
            i++;
        }

        str +=");";
        return str;
    }

    //Expressions
    public Object visit(AddOp e) throws SemanticErrorException {
        String str =" ";

        if(isWriting)
            return e.getType_node();

        str += e.left.accept(this);
        str += "+";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(AndOp e) throws SemanticErrorException {
        String str =" ";

        if(isWriting)
            return e.getType_node();

        str += e.left.accept(this);
        str += "&&";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(Char_const e) {
        String str =" ";


        str += e.attrib;

        str +=" ";
        return str;
    }

    public Object visit(DiffOp e) throws SemanticErrorException {
        String str =" ";

        if(isWriting)
            return e.getType_node();

        str += e.left.accept(this);
        str += "-";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(DivIntOp e) throws SemanticErrorException {
        String str =" ";

        if(isWriting)
            return e.getType_node();

        str += e.left.accept(this);
        str += "-";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(DivOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += "/";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(EQOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += "==";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(False_const e) {


        String str =" ";

        str += "" + e.false_const;

        str +=" ";
        return str;
    }

    public Object visit(GEOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += ">=";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(GTOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += ">";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(Identifier e) {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.attrib;

        str +="";
        return str;
    }

    public Object visit(Integer_const e) {

        String str =" ";

        str += e.attrib;

        str +=" ";
        return str;
    }

    public Object visit(LEOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += "<=";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(LTOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += "<";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(MulOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += "*";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(NEOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";
        str += e.left.accept(this);
        str += " < ";
        str += e.right.accept(this);

        return str;
    }

    public Object visit(NotOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str ="!";

        str += e.exp.accept(this);

        str +="";
        return str;
    }

    public Object visit(OrOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str += e.left.accept(this);
        str += "||";
        str += e.right.accept(this);

        str +=" ";
        return str;
    }

    public Object visit(PowOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str =" ";

        str = "pow(";
        str += e.left.accept(this);
        str += ",";
        str += e.right.accept(this);
        str+= ")";
        str +=" ";
        return str;
    }

    public Object visit(Real_const e) {


        String str =" ";

        str += "" + e.attrib;

        str +=" ";
        return str;
    }

    public Object visit(StrCatOp e) throws SemanticErrorException {

        if(isWriting)
            return STRING;

        String str ="";
        str += "strcat(";
        str += e.left.accept(this);
        str += ",";
        str += e.right.accept(this);
        str+= ")";
        str +=" ";
        return str;
    }

    public Object visit(String_const e) {

        String str =" ";

        if(isWriting)
            return e.attrib.substring(1, e.attrib.length()-1);
        str += e.attrib;

        str +=" ";
        return str;
    }

    public Object visit(True_const e) {

        String str =" ";

        str += "" + e.true_const;

        str +=" ";
        return str;
    }

    public Object visit(UminusOp e) throws SemanticErrorException {

        if(isWriting)
            return e.getType_node();

        String str ="-";

        str += e.exp.accept(this);

        str +=" ";
        return str;
    }

    @Override
    public Object visit(ParamDeclList e) throws SemanticErrorException {
        String str =" ";

        for(ParDeclOp op: e.parDeclOps)
            str += op.accept(this) + ",";

        str = str.substring(0, str.length()-1);
        return str;
    }

    @Override
    public Object visit(VarDeclList e) throws SemanticErrorException {
        String str ="";

        for(VarDeclOp op: e.varDeclOps)
            str += op.accept(this) + "";

        str += "";
        return str;
    }

    @Override
    public Object visit(IdInit e) throws SemanticErrorException {
        String str ="";

        str += e.id.accept(this);
        if(e.assign != null)
            str += " = " + e.assign.accept(this);
        if(e.assignConst !=null)
            str += e.assignConst.accept(this);

        if(str.substring(str.length()-1,str.length()).compareTo(";")==0)
            str = str.substring(0, str.length()-1);
        else if(str.substring(str.length()-2,str.length()-1).compareTo(";")==0)
            str = str.substring(0, str.length()-2);
        str += "";
        return str;
    }

    @Override
    public Object visit(IdInitList e) throws SemanticErrorException {
        String str =" ";

        for(IdInit idInit: e.idInits)
            str += idInit.accept(this) + ",";

        str = str.substring(0, str.length()-1);
        if(str.substring(str.length()-1, str.length()).compareTo(";")==0)
            str += "; ";

        return str;
    }

    @Override
    public Object visit(IdInitObblList e) throws SemanticErrorException {
        return null;    // non è possibile chiamarla
    }

    @Override
    public Object visit(StatementList e) throws SemanticErrorException {
        String str =" ";

        for(StatOp stmt: e.statList)
            if(stmt != null)
                str += stmt.accept(this) + "\n";

        str += "\n";
        return str;
    }

    @Override
    public Object visit(IdentifierList e) throws SemanticErrorException {
        String str =" ";

        for(Identifier id: e.ids)
            str += id.accept(this) + ",";

        str = str.substring(0, str.length()-1);
        str += " ";
        return str;
    }

    @Override
    public Object visit(ExpressionList e) throws SemanticErrorException {
        String str ="";

        if(isWriting) {
            ArrayList<String> tipi= new ArrayList<>();
            for(Exp exp: e.expList)
                tipi.add((String) exp.accept(this));
            return tipi;
        }
        for(Exp exp: e.expList)
            str += exp.accept(this) + " ";

        str += " ";
        return str;
    }


    private String convertiTipi(String tipo){
        switch (tipo){
            case INTEGERNEW : return INTEGERC;
            case FLOAT: return FLOAT;
            case CHAR: return CHAR;
            case STRING : return STRING;
            case VOID: return VOID;
            case BOOL: return BOOL;
        }
        return null;
    }


    public static final String INTEGERNEW = "integer";
    public static final String INTEGERC = "int";
    public static final String FLOAT = "float";
    public static final String CHAR = "char";
    public static final String STRING = "string";
    public static final String VOID = "void";
    public static final String BOOL = "bool";
}
