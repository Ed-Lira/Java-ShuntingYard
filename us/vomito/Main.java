package us.vomito;

import us.vomito.operators.*;
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
                        new NegativeOperator()

                };
        Function[] funcs =
                {
                        new SineFunction(),
                        new CosineFunction(),
                        new AbsoluteValueFunction()
                };
        MathTokenizer ml = new MathTokenizer(ops, funcs);
        Stack<Token> st = ml.tokenize("sin(x)", true);

        for (Token s : st) {
            System.out.println(s);
        }

        System.out.println("\n\n\n\n\n\nParsing...\n");

        MathParser mp = new MathParser(ops);
        ReversePolishNotation rpn = mp.stackToReversePolish(st);
        Stack<Token> parsed = rpn.tokens;
        for (Token s : parsed) {
            System.out.println(s);
        }


        System.out.println("\n\n\n\n\n\nEvaluating...\n");

        System.out.print(rpn.replaceVar("x", 90).evaluate());
    }
}
