package us.vomito;

import us.vomito.Exception.MismatchedParenthesisException;
import us.vomito.Exception.UnrecognizedTokenException;
import us.vomito.operators.Operator;
import us.vomito.operators.functions.Function;

import java.util.Stack;

public class InfixNotation {
    Operator[] ops;
    Function[] functions;
    String originalExpression;
    Stack<Token> tokens;
    ReversePolishNotation rpn;

    public InfixNotation(String expression, Operator[] ops, Function[] funcs) throws UnrecognizedTokenException, MismatchedParenthesisException {
        originalExpression = expression;
        this.ops = ops;
        this.functions = funcs;
        MathTokenizer mt = new MathTokenizer(ops, funcs);
        tokens = mt.tokenize(expression, false);
        MathParser mp = new MathParser(ops);
        rpn = mp.stackToReversePolish(tokens, expression);
    }

    public String getOriginalExpression() {
        return originalExpression;
    }

    public ReversePolishNotation getReversePolishNotation() {
        return rpn;
    }
}
