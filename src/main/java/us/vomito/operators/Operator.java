package us.vomito.operators;

import us.vomito.infix.Token;

import java.util.regex.Pattern;

public abstract class Operator {
    public String symbol;
    public Pattern pattern;
    public int precedence;
    public int operands;
    public Associativity assoc;
    public boolean includeWhenDelimiting;

    public enum Direction {LEFT, RIGHT}

    public enum Associativity {LEFT, RIGHT}

    public int arguements;

    public Operator() {
    }

    public String getSymbol() {
        return symbol;
    }

    public Pattern getPattern() {
        if (pattern == null) {
            pattern = Pattern.compile(Pattern.quote(symbol), Pattern.CASE_INSENSITIVE);
        }
        return pattern;
    }

    public Boolean testIfIsThisOperator(Object leftToken, String thisToken, Object rightToken) {
        return (symbol.equalsIgnoreCase(thisToken));
    }

    public int getPrecedence() {
        return precedence;
    }

    public double execute(double[] values) {
        return 0;
    }

    public boolean isValueOrGrouping(Object token, Direction dir) {
        if (token instanceof Token) {
            Token tok = (Token) token;
            switch (dir) {
                case LEFT:
                    return tok.isNumberOrVar() || tok.getOperator() instanceof RightGroupingOperator;
                case RIGHT:
                    return tok.isNumberOrVar() || tok.getOperator() instanceof LeftGroupingOperator;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

}
