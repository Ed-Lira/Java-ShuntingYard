package us.vomito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import us.vomito.infix.SyntaxSystem;
import us.vomito.operators.*;
import us.vomito.operators.booleanOperators.*;
import us.vomito.operators.functions.AbsoluteValueFunction;
import us.vomito.operators.functions.CosineFunction;
import us.vomito.operators.functions.Function;
import us.vomito.operators.functions.SineFunction;

public class MathTest {

    private SyntaxSystem synSys;

    @Before
    public void prepare(){
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
        synSys = new SyntaxSystem.Builder()
                .addOperators(ops)
                .addFunctions(funcs)
                .build();
    }


    @Test
    public void simpleMathTest() throws Exception{
        Assert.assertEquals(1+1,synSys.simpleSolve("1+1"),0);
        Assert.assertEquals(2*3,synSys.simpleSolve("2*3"),0);
        Assert.assertEquals(4d/3d,synSys.simpleSolve("4/3"),0);


    }
}