package us.vomito.operators.functions;

public class SineFunction extends Function {

    public SineFunction() {
        super("sin", 1);
    }

    @Override
    public double execute(double[] values) {
        return Math.sin(Math.toRadians(values[0]));
    }
}
