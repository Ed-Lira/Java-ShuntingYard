package us.vomito;

import us.vomito.Variables.Variable;
import us.vomito.operators.LeftGroupingOperator;
import us.vomito.operators.Operator;
import us.vomito.operators.RightGroupingOperator;

public class Token {

    private Operator operator;
    private double number;
    private Variable variable;
    private boolean isOperator;
    private boolean isNumber;
    private boolean isParenthesis;

    public enum TokenType {OPERATOR, NUMBER, PARENTHESIS, FUNCTION, VARIABLE}

    public TokenType type;

    public Token(Operator o) {
        this.operator = o;
        this.isOperator = true;
        this.isNumber = false;
        this.isParenthesis = o instanceof LeftGroupingOperator || o instanceof RightGroupingOperator;

        this.type = TokenType.OPERATOR;
    }

    public Token(double number) {
        this.number = number;
        this.isOperator = false;
        this.isNumber = true;
        this.isParenthesis = false;

        this.type = TokenType.NUMBER;
    }

    public Token(Variable var) {
        this.variable = var;
        this.isOperator = false;
        this.isNumber = false;
        this.isParenthesis = false;

        this.type = TokenType.VARIABLE;
    }

    public Operator getOperator() {
        return operator;
    }

    public double getNumber() {
        return number;
    }

    public Variable getVariable() {
        return variable;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isNumberOrVar() {
        switch (this.type) {
            case NUMBER:
            case VARIABLE:
                return true;
            default:
                return false;
        }
    }

    public boolean isParenthesis() {
        return isParenthesis;
    }

    public boolean isValue() {
        switch (this.type) {
            case PARENTHESIS:
            case NUMBER:
            case VARIABLE:
                return true;
            default:
                return false;
        }
    }

    public String toString() {
        switch (this.type) {
            case PARENTHESIS:
            case FUNCTION:
            case OPERATOR:
                return this.getOperator().symbol + " " + this.getOperator().getClass().getSimpleName();
            case NUMBER:
                return this.getNumber() + "";
            case VARIABLE:
                return this.variable.symbol + " " + this.variable.getClass().getSimpleName();
            default:
                return "?";
        }
    }
}
