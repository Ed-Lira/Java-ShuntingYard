package us.vomito.operators.functions;

public class CosineFunction extends Function {

    public CosineFunction() {
        super("cos", 1);
    }

    @Override
    public double execute(double[] values) {
        return Math.cos(Math.toRadians(values[0]));
    }
}
