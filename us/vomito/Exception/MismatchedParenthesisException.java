package us.vomito.Exception;

public class MismatchedParenthesisException extends SyntaxException {
    String expression;
    int location;

    public MismatchedParenthesisException(String expression, int location) {
        super("Need closing Parenthesis in \"" + expression + "\" at position " + location);
        this.expression = expression;
        this.location = location;
    }

    public String getMultiLineMessage() {
        String r = "Need closing Parenthesis for:\n";
        return r + formatMultiLineMessage(expression, location);
    }
}
