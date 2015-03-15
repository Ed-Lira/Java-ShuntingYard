package us.vomito.operators;

public class MultiplicationOperator extends Operator {

    public MultiplicationOperator() {
        this.symbol = "*";
        this.includeWhenDelimiting = true;
        this.precedence = 6;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return values[0] * values[1];
    }
}
