package us.vomito.operators;

public class SubtractionOperator extends Operator {

    public SubtractionOperator() {
        this.symbol = "-";
        this.includeWhenDelimiting = true;
        this.precedence = 5;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return values[1] - values[0];
    }

    @Override
    public Boolean testIfIsThisOperator(Object leftToken, String thisToken, Object rightToken) {
        return (isValueOrGrouping(leftToken, Direction.LEFT) && this.symbol.equalsIgnoreCase(thisToken) && isValueOrGrouping(rightToken, Direction.RIGHT));
    }
}
