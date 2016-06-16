package us.vomito.operators.booleanOperators;

import us.vomito.operators.Operator;
import us.vomito.operators.OperatorUtil;

public class EqualToOperator extends Operator {

    public EqualToOperator() {
        this.symbol = "==";
        this.includeWhenDelimiting = true;
        this.assoc = Associativity.LEFT;
        this.precedence = 5;
        this.arguements = 2;
    }

    @Override
    public double execute(double[] values) {
        return OperatorUtil.booleanToDouble(values[1] == values[0]);
    }


}
