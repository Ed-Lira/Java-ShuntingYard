package us.vomito.infix;

import us.vomito.Exception.MismatchedParenthesisException;
import us.vomito.operators.LeftGroupingOperator;
import us.vomito.operators.Operator;
import us.vomito.operators.RightGroupingOperator;

import java.util.Stack;

public class MathParser {

    public MathParser() {
    }

    public ReversePolishNotation stackToReversePolish(Stack<Token> input, String originalExpression) throws MismatchedParenthesisException {
        Stack<Token> output = new Stack<Token>();
        Stack<Token> operators = new Stack<Token>();
        int index = 0;
        int size = input.size();
        while (index < size) {
            Token current = input.elementAt(index);
            if (current.isNumberOrVar()) {
                output.add(current);
            }//support for functions would go here
            else if (current.isOperator() && !current.isParenthesis()) { //is non-grouping operator
                Operator currentOperator = current.getOperator();
                while (!operators.empty() && !(operators.peek().isParenthesis()) && ((currentOperator.assoc == Operator.Associativity.LEFT && currentOperator.precedence <= operators.peek().getOperator().precedence) || (currentOperator.assoc == Operator.Associativity.RIGHT && currentOperator.precedence < operators.peek().getOperator().precedence))) {
                    output.add(operators.pop());
                }
                operators.push(current);
            } else if (current.getOperator() instanceof LeftGroupingOperator) {
                operators.push(current);
            } else if (current.getOperator() instanceof RightGroupingOperator) {
                operators.firstElement().toString();
                while (!operators.empty() && !(operators.peek().getOperator() instanceof LeftGroupingOperator)) {
                    output.add(operators.pop());
                }
                if (!operators.empty() && operators.peek().getOperator() instanceof LeftGroupingOperator) {
                    operators.pop();
                } else {
                    throw new MismatchedParenthesisException(originalExpression, current.getLocation());
                }
                //If the token at the top of the stack is a function token, pop it onto the output queue.
            }
            index++;
        }
        //when no more tokens to read
        while (!operators.isEmpty()) {

            if (operators.firstElement().getOperator() instanceof LeftGroupingOperator || operators.firstElement().getOperator() instanceof LeftGroupingOperator) {
                throw new MismatchedParenthesisException(originalExpression, originalExpression.length());
            }
            output.add(operators.pop());

        }

        return (new ReversePolishNotation(output));

    }
}
