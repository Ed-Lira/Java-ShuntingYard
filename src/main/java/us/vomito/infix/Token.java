package us.vomito.infix;

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
    private int location;
    private int originalLength;

    public enum TokenType {OPERATOR, NUMBER, PARENTHESIS, FUNCTION, VARIABLE}

    public TokenType type;

    public Token(Operator o, int location) {
        this.location = location;
        this.operator = o;
        originalLength = o.getSymbol().length();
        this.isOperator = true;
        this.isNumber = false;
        this.isParenthesis = o instanceof LeftGroupingOperator || o instanceof RightGroupingOperator;
        this.type = TokenType.OPERATOR;
    }

    public Token(double number, int location, int orgLength) {
        this.originalLength = orgLength;
        this.location = location;
        this.number = number;
        this.isOperator = false;
        this.isNumber = true;
        this.isParenthesis = false;

        this.type = TokenType.NUMBER;
    }

    public Token(Variable var, int location) {
        this.location = location;
        this.variable = var;
        this.originalLength = this.variable.symbol.length();
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

    public void setNumber(double num) {
        this.number = num;
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

    public int getLocation() {
        return location;
    }

    public int getOriginalLength() {
        return originalLength;
    }

    public String toString() {
        switch (this.type) {
            case PARENTHESIS:
            case FUNCTION:
            case OPERATOR:
                return this.getOperator().symbol + " " + this.getOperator().getClass().getSimpleName() + " " + getLocation();
            case NUMBER:
                return this.getNumber() + " " + getLocation();
            case VARIABLE:
                return this.variable.symbol + " " + this.variable.getClass().getSimpleName() + " " + getLocation();
            default:
                return "?";
        }
    }
}
