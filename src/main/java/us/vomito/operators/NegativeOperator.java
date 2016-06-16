package us.vomito.operators;

public class NegativeOperator extends Operator {

    public NegativeOperator() {
        this.symbol = "-";
        this.includeWhenDelimiting = true;
        this.precedence = 8;
        this.arguements = 1;
    }

    @Override
    public double execute(double[] values) {
        return 0 - values[0];
    }

}
