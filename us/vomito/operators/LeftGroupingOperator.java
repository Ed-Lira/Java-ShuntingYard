package us.vomito.operators;


public class LeftGroupingOperator extends Operator {

    public LeftGroupingOperator() {
        this.symbol = "(";
        this.precedence = 9;
        this.includeWhenDelimiting = true;
    }
}
