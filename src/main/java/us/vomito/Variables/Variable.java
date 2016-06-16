package us.vomito.Variables;

import java.util.regex.Pattern;

public class Variable {
    public String symbol;
    public Pattern pattern;

    public Variable(String symbol) {
        this.symbol = symbol;
    }
}
