package TypeChecking;

import SymbolTable.SemanticErrorException;

public class OperatorsTables {

    public static String opt(String operator, String type) throws SemanticErrorException {

        switch (operator){
            case MINUS :
                if(type.equals("integer"))
                    return "integer";
                else if(type.equals("float"))
                    return "float";
                else
                    throwException(type, operator);
                break;

            case NOT:
                if(type.equals("boolean"))
                    return "boolean";
                else
                    throwException(type, operator);
                break;
            default:
                throwException(operator);
        }
        return null;
    }

    public static String opt(String operator, String typeLeft, String typeRight) throws SemanticErrorException {

        switch (operator){
            case PLUS:
            case MINUS:
            case TIMES:
            case DIV:
            case POW:
                if(typeLeft.equals("integer") && typeRight.equals("integer"))
                    return "integer";
                else if(typeLeft.equals("integer") && typeRight.equals("float"))
                    return "float";
                else if(typeLeft.equals("float") && typeRight.equals("integer"))
                    return "float";
                else if(typeLeft.equals("float") && typeRight.equals("float"))
                    return "float";
                else
                    throwException(typeLeft,typeRight,operator);
                break;

            case STR_CONCAT:
                if(typeLeft.equals("string") && typeRight.equals("string"))
                    return "string";
                else
                    throwException(typeLeft,typeRight,operator);
                break;

            case AND:
            case OR:
                if(typeLeft.equals("boolean") && typeRight.equals("boolean"))
                    return "boolean";
                else
                    throwException(typeLeft,typeRight,operator);
                break;

            case GT:
            case GE:
            case LT:
            case LE:
            case EQ:
            case NE:
                if(typeLeft.equals("integer") && typeRight.equals("integer"))
                    return "boolean";
                else if(typeLeft.equals("integer") && typeRight.equals("float"))
                    return "boolean";
                else if(typeLeft.equals("float") && typeRight.equals("integer"))
                    return "boolean";
                else if(typeLeft.equals("float") && typeRight.equals("float"))
                    return "boolean";
                else
                    throwException(typeLeft,typeRight,operator);
                break;

            default:
                throwException(operator);

        }
        return null;
    }

    private static void throwException(String typeLeft,String typeRight, String operator) throws SemanticErrorException {
        throw new SemanticErrorException("Errore tipo " + typeLeft + " e "+ typeRight + " non supportato per operazione " + operator);
    }

    private static void throwException(String type, String operator) throws SemanticErrorException {
        throw new SemanticErrorException("Errore tipo " + type + " non supportato per operazione " + operator);
    }

    private static void throwException(String operator) throws SemanticErrorException {
        throw new SemanticErrorException("Errore operazione non supportata: " + operator);
    }



    public static final String MINUS = "minus";
    public static final String NOT = "not";
    public static final String PLUS = "plus";
    public static final String TIMES = "times";
    public static final String DIV = "div";
    public static final String POW = "pow";
    public static final String STR_CONCAT = "str_concat";
    public static final String AND= "and";
    public static final String OR = "or";
    public static final String GT = "gt";
    public static final String GE = "ge";
    public static final String LT = "lt";
    public static final String LE = "le";
    public static final String EQ = "eq";
    public static final String NE = "ne";
}
