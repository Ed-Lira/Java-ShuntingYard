package us.vomito.operators;

public class ExponentiationOperator extends Operator {

    public ExponentiationOperator() {
        this.symbol = "^";
        this.includeWhenDelimiting = true;
        this.assoc = Associativity.RIGHT;
        this.precedence = 7;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return Math.pow(values[0], values[1]);
    }
}
