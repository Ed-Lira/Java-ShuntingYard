package us.vomito;

import us.vomito.Exception.UnrecognizedTokenException;
import us.vomito.Variables.Variable;
import us.vomito.operators.Operator;
import us.vomito.operators.functions.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MathTokenizer {
    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    private List<Operator> operators = new ArrayList<Operator>();
    private List<Function> functions = new ArrayList<Function>();
    public String expression;

    private MathTokenizer() {}

    public Stack<Token> tokenize(String expression, Boolean noSpacing) throws UnrecognizedTokenException {
        this.expression = expression;
        Stack<String> tokenStrings = lexExpression(expression, noSpacing);
        Stack<Object> numbersTokenized = tokenizeNumbers(tokenStrings);
        Stack<Object> funcsTokenized = tokenizeFunctions(numbersTokenized);
        Stack<Object> variablesTokenized = tokenizeVariables(funcsTokenized);
        Stack<Object> opsTokenized = tokenizeOperators(variablesTokenized);

        return cleanupTokens(opsTokenized);
    }

    public Stack<String> lexExpression(String input, Boolean noSpacing) {

        if (noSpacing)
            input = input.replaceAll("\\s+", "");

        Stack<String> original = new Stack<String>();
        original.add(input);

        Stack<String> current = original;
        Stack<String> next = new Stack<String>();
        for (Operator operator : operators) {

            for (String partial : current) {

                String regex = operator.includeWhenDelimiting ? String.format(WITH_DELIMITER, operator.getPattern().toString()) : operator.getPattern().toString();

                String[] toAdd = partial.split(regex);

                for (String s : toAdd) {
                    if (!s.equalsIgnoreCase(""))
                        next.add(s);
                }
            }


            current = new Stack<String>();
            current.addAll(next);
            next.clear();
        }

        Stack<String> tokens = current;
        return tokens;
    }

    public Stack<Object> tokenizeVariables(Stack<Object> functionParsed) {
        int i = 0;
        int size = functionParsed.size();
        int location = 0;
        while (i < size) {
            Object token = functionParsed.elementAt(i);
            int length = 0;
            if (token instanceof String) {
                String tokenSt = (String) token;
                boolean isOperator = false;
                for (Operator operator : operators) {
                    isOperator = operator.getSymbol().equalsIgnoreCase(tokenSt);

                    if (isOperator) {
                        length = tokenSt.length();
                        break;
                    }
                }
                if (!isOperator) {
                    functionParsed.set(i, new Token(new Variable(tokenSt), location));
                    length = ((String) token).length();
                }

            } else if (token instanceof Token) {
                length = ((Token) token).getOriginalLength();
            }
            location += length;
            i++;
        }
        return functionParsed;
    }

    public Stack<Object> tokenizeFunctions(Stack<Object> numberParsed) {
        int i = 0;
        int size = numberParsed.size();
        int location = 0;
        while (i < size) {
            Object token = numberParsed.elementAt(i);
            int length = 0;
            if (token instanceof String) {
                String strToken = (String) token;
                length = strToken.length();
                for (Function function : functions) {
                    boolean isFunction = function.isThisFunction((String) token);
                    if (isFunction) {
                        numberParsed.set(i, new Token(function, location));
                        break;
                    }
                }
            } else {
                if (token instanceof Token) {
                    length = ((Token) token).getOriginalLength();
                }

            }
            location += length;
            i++;
        }
        return numberParsed;
    }

    public Stack<Object> tokenizeOperators(Stack<Object> variableParsed) {
        //Stack<Object> parsedTokens = new Stack<Object>();
        int i = 0;
        int size = variableParsed.size();
        int location = 0;
        int length = 0;
        while (i < size) {
            Object token = variableParsed.elementAt(i);
            if (token instanceof String) {
                Object lasttoken = i - 1 < 0 ? null : variableParsed.elementAt(i - 1);
                Object nexttoken = i + 1 >= size ? null : variableParsed.elementAt(i + 1);
                for (Operator operator : operators) {
                    boolean isOperator = operator.testIfIsThisOperator(lasttoken, (String) token, nexttoken);
                    if (isOperator) {
                        length = ((String) token).length();
                        variableParsed.set(i, new Token(operator, location));
                        break;
                    }
                }

            } else if (token instanceof Token) {
                length = ((Token) token).getOriginalLength();
            }
            location += length;
            i++;
        }
        return variableParsed;
    }

    public Stack<Object> tokenizeNumbers(Stack<String> inputStack) {
        Stack<Object> numParsedStack = new Stack<Object>();
        int i = 0;
        int location = 0;
        int size = inputStack.size();
        while (i < size) {
            String token = inputStack.elementAt(i);
            int length = token.length();
            try {
                Double number = Double.parseDouble(token);
                numParsedStack.add(new Token(number, location, length));

            } catch (NumberFormatException nfe) {
                numParsedStack.add(token);
            }
            location += length;
            i++;
        }
        return numParsedStack;
    }

    private Stack<Token> cleanupTokens(Stack<Object> tokens) throws UnrecognizedTokenException {
        Stack<Token> cleanTokens = new Stack<Token>();
        int location = 0;
        for (Object t : tokens) {
            if (t instanceof Token) {
                location += ((Token) t).getOriginalLength();
                cleanTokens.add((Token) t);
            } else if (t instanceof String) {
                throw new UnrecognizedTokenException((String) t, expression, location);
            }
        }
        return cleanTokens;
    }


    public static class Builder{
        private MathTokenizer tokenizer;
        public Builder(){
            tokenizer = new MathTokenizer();
        }

        public Builder addOperator(Operator o){
            tokenizer.operators.add(o);
            return this;
        }

        public Builder addFunction(Function f){
            tokenizer.functions.add(f);
            return this;
        }

        public Builder addOperators(Operator[] ops){
            Collections.addAll(tokenizer.operators,ops);
            return this;
        }

        public Builder addFunctions(Function[] funs){
            Collections.addAll(tokenizer.functions, funs);
            return this;
        }

        public MathTokenizer build(){
            return tokenizer;
        }
    }

}
