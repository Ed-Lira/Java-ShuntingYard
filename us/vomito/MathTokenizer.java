package us.vomito;

import us.vomito.Variables.Variable;
import us.vomito.operators.Operator;
import us.vomito.operators.functions.Function;

import java.util.Stack;

public class MathTokenizer {
    static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    private Operator[] operators;
    private Function[] functions;

    public MathTokenizer(Operator[] operators, Function[] functions) {
        this.operators = operators;
        this.functions = functions;
    }

    public Stack<Token> tokenize(String expression, Boolean noSpacing) {
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
        while (i < size) {
            Object token = functionParsed.elementAt(i);
            if (token instanceof String) {
                String tokenSt = (String) token;
                boolean isOperator = false;
                for (Operator operator : operators) {
                    isOperator = operator.getSymbol().equalsIgnoreCase(tokenSt);

                    if (isOperator) {
                        break;
                    }
                }
                if (!isOperator) {
                    functionParsed.set(i, new Token(new Variable(tokenSt)));
                }

            }
            i++;
        }
        return functionParsed;
    }

    public Stack<Object> tokenizeFunctions(Stack<Object> numberParsed) {
        int i = 0;
        int size = numberParsed.size();
        while (i < size) {
            Object token = numberParsed.elementAt(i);
            if (token instanceof String) {
                for (Function function : functions) {
                    boolean isFunction = function.isThisFunction((String) token);
                    if (isFunction) {
                        numberParsed.set(i, new Token(function));
                        break;
                    }
                }
            }
            i++;
        }
        return numberParsed;
    }

    public Stack<Object> tokenizeOperators(Stack<Object> variableParsed) {
        //Stack<Object> parsedTokens = new Stack<Object>();
        int i = 0;
        int size = variableParsed.size();
        while (i < size) {
            Object token = variableParsed.elementAt(i);
            if (token instanceof String) {
                Object lasttoken = i - 1 < 0 ? null : variableParsed.elementAt(i - 1);
                Object nexttoken = i + 1 >= size ? null : variableParsed.elementAt(i + 1);
                for (Operator operator : operators) {
                    boolean isOperator = operator.testIfIsThisOperator(lasttoken, (String) token, nexttoken);
                    if (isOperator) {
                        variableParsed.set(i, new Token(operator));
                        break;
                    }
                }

            }
            i++;
        }
        return variableParsed;
    }

    public Stack<Object> tokenizeNumbers(Stack<String> inputStack) {
        Stack<Object> numParsedStack = new Stack<Object>();
        for (String token : inputStack) {
            try {
                Double number = Double.parseDouble(token);
                numParsedStack.add(new Token(number));
            } catch (NumberFormatException nfe) {
                numParsedStack.add(token);
            }
        }
        return numParsedStack;
    }

    private Stack<Token> cleanupTokens(Stack<Object> tokens) {
        Stack<Token> cleanTokens = new Stack<Token>();
        for (Object t : tokens) {
            if (t instanceof Token) {
                cleanTokens.add((Token) t);
            } else {
                System.out.println("Could not recognize " + "\"" + t + "\"");
            }
        }
        return cleanTokens;
    }
}
