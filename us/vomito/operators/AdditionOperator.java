package us.vomito.operators;

public class AdditionOperator extends Operator {

    public AdditionOperator() {
        this.symbol = "+";
        this.includeWhenDelimiting = true;
        this.assoc = Associativity.LEFT;
        this.precedence = 5;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return values[0] + values[1];
    }


}
