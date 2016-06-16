package us.vomito.Exception;

public class SyntaxException extends Exception {

    public SyntaxException(String message) {
        super(message);
    }

    public String formatMultiLineMessage(String originalExpression, int location) {
        StringBuilder sb = new StringBuilder(originalExpression + "\n");
        for (int x = 0; x < location; x++) {
            sb.append(" ");
        }
        sb.append("^");
        return sb.toString();
    }
}

