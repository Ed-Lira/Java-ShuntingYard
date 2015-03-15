package us.vomito.operators;

public class DivisionOperator extends Operator {

    public DivisionOperator() {
        this.symbol = "/";
        this.includeWhenDelimiting = true;
        this.assoc = Associativity.LEFT;
        this.precedence = 6;
        this.arguements = 2;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return values[0] / values[1];
    }


}
