package us.vomito.operators.functions;

public class AbsoluteValueFunction extends Function {


    public AbsoluteValueFunction() {
        super("abs", 1);
    }

    @Override
    public double execute(double[] values) {
        return Math.abs(values[0]);
    }
}
