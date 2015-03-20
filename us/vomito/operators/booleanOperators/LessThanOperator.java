package us.vomito.operators.booleanOperators;

import us.vomito.operators.Operator;
import us.vomito.operators.OperatorUtil;

public class LessThanOperator extends Operator {

    public LessThanOperator() {
        this.symbol = "<";
        this.includeWhenDelimiting = true;
        this.assoc = Associativity.LEFT;
        this.precedence = 5;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return OperatorUtil.booleanToDouble(values[1] < values[0]);
    }


}
