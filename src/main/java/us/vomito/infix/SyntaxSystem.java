package us.vomito.infix;

import us.vomito.Exception.MismatchedParenthesisException;
import us.vomito.Exception.UnrecognizedTokenException;
import us.vomito.operators.Operator;
import us.vomito.operators.functions.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyntaxSystem {
    private List<Operator> operators = new ArrayList<Operator>();
    private List<Function> functions = new ArrayList<Function>();
    private MathTokenizer tokenizer;
    private MathParser parser;

    private SyntaxSystem() {}

    public double simpleSolve(String expression) throws UnrecognizedTokenException, MismatchedParenthesisException {
        return buildExpression(expression)
                .getReversePolishNotation()
                .evaluate();
    }

    public InfixNotation buildExpression(String expression) throws UnrecognizedTokenException, MismatchedParenthesisException {
        return new InfixNotation(expression,tokenizer,parser);
    }

    public static class Builder{
        private SyntaxSystem synSys;
        public Builder(){
            synSys = new SyntaxSystem();
        }

        public Builder addOperator(Operator o){
            synSys.operators.add(o);
            return this;
        }

        public Builder addFunction(Function f){
            synSys.functions.add(f);
            return this;
        }

        public Builder addOperators(Operator[] ops){
            Collections.addAll(synSys.operators,ops);
            return this;
        }

        public Builder addFunctions(Function[] funs){
            Collections.addAll(synSys.functions, funs);
            return this;
        }

        public SyntaxSystem build(){
            synSys.tokenizer = new MathTokenizer(synSys.operators,synSys.functions);
            synSys.parser = new MathParser();
            return synSys;
        }
    }
}
