package us.vomito.operators.functions;

import us.vomito.operators.Operator;

public class Function extends Operator {

    public Function(String identifier, int arguements) {
        this.symbol = identifier;
        this.includeWhenDelimiting = true;
        this.precedence = 4;
        this.arguements = arguements;
    }

    public boolean isThisFunction(String id) {
        return this.symbol.equalsIgnoreCase(id);
    }
}
