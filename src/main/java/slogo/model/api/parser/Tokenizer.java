package slogo.model.api.parser;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private String input;
    private int currentPosition;

    public Tokenizer() {
        this.input = "";
        this.currentPosition = 0;
    }

    public void setInput(String input) {
        this.input = input;
        this.currentPosition = 0; // Reset position for new input
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);

            if (Character.isWhitespace(currentChar)) {
                currentPosition++; // Skip whitespace
            } else if (Character.isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, consumeNumber()));
            } else if (currentChar == ':') {
                tokens.add(new Token(TokenType.VARIABLE, consumeVariable()));
            } else if ("[]".indexOf(currentChar) != -1) {
                tokens.add(new Token(TokenType.SYMBOL, String.valueOf(currentChar)));
                currentPosition++;
            } else if (Character.isLetter(currentChar)) {
                tokens.add(new Token(TokenType.IDENTIFIER, consumeIdentifier()));
            } else {
                tokens.add(new Token(TokenType.UNKNOWN, String.valueOf(currentChar)));
                currentPosition++;
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private String consumeNumber() {
        int start = currentPosition;
        while (currentPosition < input.length() && Character.isDigit(input.charAt(currentPosition))) {
            currentPosition++;
        }
        return input.substring(start, currentPosition);
    }

    private String consumeVariable() {
        int start = currentPosition++;
        while (currentPosition < input.length() && Character.isLetterOrDigit(input.charAt(currentPosition))) {
            currentPosition++;
        }
        return input.substring(start, currentPosition);
    }

    private String consumeIdentifier() {
        int start = currentPosition;
        while (currentPosition < input.length() && Character.isLetter(input.charAt(currentPosition))) {
            currentPosition++;
        }
        return input.substring(start, currentPosition);
    }
}

