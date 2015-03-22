package us.vomito.Exception;

public class UnrecognizedTokenException extends SyntaxException {
    String token;
    String expression;
    int location;

    public UnrecognizedTokenException(String token, String expression, int location) {
        super("Unrecognized token \"" + token + "\" in \"" + expression + "\" at position " + location);
        this.token = token;
        this.expression = expression;
        this.location = location;
    }

    public String getMultiLineMessage() {
        String r = "Unrecognized token \"" + token + "\" in:\n";
        return r + formatMultiLineMessage(expression, location);
    }
}
