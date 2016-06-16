package us.vomito.operators;

public class RightGroupingOperator extends Operator {

    public RightGroupingOperator() {
        this.symbol = ")";
        this.precedence = 9;
        this.includeWhenDelimiting = true;
    }

}
