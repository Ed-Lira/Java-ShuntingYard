package us.vomito;

import us.vomito.Exception.MismatchedParenthesisException;
import us.vomito.Exception.UnrecognizedTokenException;
import us.vomito.infix.InfixNotation;
import us.vomito.infix.ReversePolishNotation;
import us.vomito.infix.SyntaxSystem;
import us.vomito.infix.Token;
import us.vomito.operators.*;
import us.vomito.operators.booleanOperators.*;
import us.vomito.operators.functions.AbsoluteValueFunction;
import us.vomito.operators.functions.CosineFunction;
import us.vomito.operators.functions.Function;
import us.vomito.operators.functions.SineFunction;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //Scanner s = new Scanner(System.in);
        //s.nextLine();
        Operator[] ops =
                {
                        new LeftGroupingOperator(),
                        new RightGroupingOperator(),
                        new AdditionOperator(),
                        new SubtractionOperator(),
                        new MultiplicationOperator(),
                        new DivisionOperator(),
                        new ExponentiationOperator(),
                        new NegativeOperator(),
                        new EqualToOperator(),
                        new NotEqualToOperator(),
                        new GreaterThanOrEqualToOperator(),
                        new LessThanOrEqualToOperator(),
                        new GreaterThanOperator(),
                        new LessThanOperator()
                };
        Function[] funcs =
                {
                        new SineFunction(),
                        new CosineFunction(),
                        new AbsoluteValueFunction()
                };


        try {
            SyntaxSystem syntaxSystem = new SyntaxSystem.Builder()
                    .addOperators(ops)
                    .addFunctions(funcs)
                    .build();
            InfixNotation in = syntaxSystem.buildExpression("sin((31.0!=1*2*x))");
            ReversePolishNotation out = in.getReversePolishNotation();
            double outValue = out.evaluate();

/**
            for (Token s : in.) {
                System.out.println(s);
            }
 */

            System.out.println("\n\n\n\n\n\nParsing...\n");

            Stack<Token> parsed = out.tokens;
            for (Token s : parsed) {
                System.out.println(s);
            }


            System.out.println("\n\n\n\n\n\nEvaluating...\n");

            System.out.print(out.replaceVar("x", 90).evaluate());
            //System.out.print(rpn.evaluate());
        } catch (UnrecognizedTokenException ute) {
            System.out.println(ute.getMultiLineMessage());
        } catch (MismatchedParenthesisException mpe) {
            System.out.println(mpe.getMultiLineMessage());
        }
    }
}
