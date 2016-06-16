package us.vomito.infix;

import us.vomito.Exception.MismatchedParenthesisException;
import us.vomito.Exception.UnrecognizedTokenException;
import us.vomito.operators.Operator;
import us.vomito.operators.functions.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class InfixNotation {
    String expression;
    Stack<Token> tokens;
    ReversePolishNotation rpn;

    protected InfixNotation(String expression, MathTokenizer tokenizer, MathParser parser) throws UnrecognizedTokenException, MismatchedParenthesisException {
        this.expression = expression;
        tokens = tokenizer.tokenize(expression, false);
        rpn = parser.stackToReversePolish(tokens, expression);
    }

    public String getExpression() {
        return expression;
    }

    public ReversePolishNotation getReversePolishNotation() {
        return rpn;
    }


}
