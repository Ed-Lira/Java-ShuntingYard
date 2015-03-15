package us.vomito;

import java.util.Stack;

public class ReversePolishNotation {
    public Stack<Token> tokens;

    public ReversePolishNotation(Stack<Token> tokens) {
        this.tokens = tokens;
    }

    public ReversePolishNotation replaceVar(String variable, double value) {
        Stack<Token> output = new Stack<Token>();
        int i = 0;
        int size = tokens.size();
        while (i < size) {
            Token current = tokens.elementAt(i);
            if (current.type == Token.TokenType.VARIABLE && current.getVariable().symbol.equalsIgnoreCase(variable)) {
                output.push(new Token(value));
            } else {
                output.push(current);
            }
            i++;
        }
        tokens = output;
        return this;
    }

    public double evaluate() {
        Stack<Token> input = tokens;
        Stack<Double> output = new Stack<Double>();
        int i = 0;
        int size = input.size();
        while (i < size) {
            Token current = input.elementAt(i);
            if (current.isNumber()) {
                output.push(current.getNumber());
            } else if (current.isOperator()) {
                int args = current.getOperator().arguements;
                int j = 0;
                double[] arguments = new double[args];
                while (j < args) {
                    arguments[j] = output.pop();
                    j++;
                }
                output.push(current.getOperator().execute(arguments));
            }


            i++;
        }
        return output.peek();
    }
}
