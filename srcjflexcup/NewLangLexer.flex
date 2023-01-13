

import java_cup.runtime.Symbol;
import Parser.sym;
import VisitorPattern.Program.*;
import VisitorPattern.Stat.*;
import VisitorPattern.Expressions.*;
import VisitorPattern.*;
import VisitorPattern.Expressions.Const.*;
import VisitorPattern.Program.IdInit.*;


%%

%public
%class Lexer
%cup
%state COMMENTO

lineTerminator = [\r\n]
whitespace = [ \t\f] | {lineTerminator}
id = [$_A-Za-z][$_A-Za-z0-9]*
digit = [0-9]
digits = {digit}+
int = (-)?{digits}
real = {int}("."{digits})?
str = (\"[^\"]*\")
char = (\'.\')
commentLine = "||" [^\r\n]* {lineTerminator}?  //aggiungere commenti
commentStart = "|*"
commentFinish = "*|"

%%
// Now for the actual tokens and assocated actions
<YYINITIAL>{
    {commentStart} { yybegin(COMMENTO); }
    {commentLine}  { System.out.println("Trovato commentoln: " + yytext());}
    "start:" { return new Symbol(sym.MAIN) ; }
    ";"     { return new Symbol(sym.SEMI); }
    ","     { return new Symbol(sym.COMMA); }
    "|"     { return new Symbol(sym.PIPE); }
    "var"   { return new Symbol(sym.VAR); }
    "integer" { return new Symbol(sym.INT); }
    "real" { return new Symbol(sym.FLOAT); }
    "string" { return new Symbol(sym.STRING); }
    "boolean" { return new Symbol(sym.BOOL); }
    "char"  { return new Symbol(sym.CHAR); }
    "void"  { return new Symbol(sym.VOID); }

    "def"   { return new Symbol(sym.DEF); }
    "out"   { return new Symbol(sym.OUT); }
    "for"   { return new Symbol(sym.FOR); }
    "if"    { return new Symbol(sym.IF); }
    "then"  { return new Symbol(sym.THEN); }
    "else"  { return new Symbol(sym.ELSE); }
    "while" { return new Symbol(sym.WHILE); }
    "to"    { return new Symbol(sym.TO); }
    "loop"  { return new Symbol(sym.LOOP); }
    "<--"   { return new Symbol(sym.READ); }
    "-->"   { return new Symbol(sym.WRITE); }
    "-->!"  { return new Symbol(sym.WRITELN); }

    "("     { return new Symbol(sym.LPAR); }
    ")"     { return new Symbol(sym.RPAR); }
    "{"     { return new Symbol(sym.LBRAC); }
    "}"     { return new Symbol(sym.RBRAC); }
    ":"     { return new Symbol(sym.COLON); }
    "<<"    { return new Symbol(sym.ASSIGN); }
    "return" { return new Symbol(sym.RETURN); }


    {int}   { return new Symbol(sym.INTEGER_CONST, new Integer_const(Integer.parseInt(yytext()))); }
    {real}  { return new Symbol(sym.REAL_CONST, new Real_const(Float.parseFloat(yytext()))); }
    {str}   { return new Symbol(sym.STRING_CONST, new String_const(yytext())); }
    {char}  { return new Symbol(sym.CHAR_CONST, new Char_const((char) yytext().charAt(1))); }

    "true"  { return new Symbol(sym.TRUE, new True_const()); }
    "false" { return new Symbol(sym.FALSE, new False_const()); }
    "+"     { return new Symbol(sym.PLUS); }
    "-"     { return new Symbol(sym.MINUS); }
    "*"     { return new Symbol(sym.TIMES); }
    "/"     { return new Symbol(sym.DIV); }
    "^"     { return new Symbol(sym.POW); }
    "&"     { return new Symbol(sym.STR_CONCAT); }
    "="     { return new Symbol(sym.EQ); }
    "<"     { return new Symbol(sym.LT); }
    "<="    { return new Symbol(sym.LE); }
    ">"     { return new Symbol(sym.GT); }
    ">="    { return new Symbol(sym.GE); }
    ["<>""!="] { return new Symbol(sym.NE); }
    "and"   { return new Symbol(sym.AND); }
    "or"    { return new Symbol(sym.OR); }
    "not"   { return new Symbol(sym.NOT); }

    {id}    { return new Symbol(sym.ID, new Identifier(yytext())); }
    {whitespace} {}
    [^]           { throw new Error("\n\nIllegal character < "+ yytext()+" >\n"); }
    // End of file
}

<COMMENTO> {
    ( [^*] | \* + [^|*] )* {commentFinish} {
        System.out.println("Trovato commento: " + yytext());
        yybegin(YYINITIAL);
    }
}
