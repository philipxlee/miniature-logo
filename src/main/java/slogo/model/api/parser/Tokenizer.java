package slogo.model.api.parser;
import slogo.model.api.parser.exceptions.InvalidTokenException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public List<Token> tokenize() throws InvalidTokenException {
        List<Token> tokens = new ArrayList<>();
        Pattern constantPattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        Pattern variablePattern = Pattern.compile(":[a-zA-Z_]+");

        while (currentPosition < input.length()) {
            char currentChar = input.charAt(currentPosition);

            if (Character.isWhitespace(currentChar)) {
                currentPosition++; // Skip whitespace
            } else if (currentChar == '#') {
                // Skip the rest of the line for comments
                while (currentPosition < input.length() && input.charAt(currentPosition) != '\n') {
                    currentPosition++;
                }
            } else if (Character.isDigit(currentChar) || currentChar == '-') {
                String constantToken = consumeRegex(constantPattern);
                if (!constantToken.isEmpty()) {
                    tokens.add(new Token(TokenType.NUMBER, constantToken));
                } else {
                    throw new InvalidTokenException("Invalid Constant: " + input.substring(currentPosition));
                }
            } else if (currentChar == ':') {
                String variableToken = consumeRegex(variablePattern);
                if (!variableToken.isEmpty()) {
                    tokens.add(new Token(TokenType.VARIABLE, variableToken));
                } else {
                    throw new InvalidTokenException("Invalid Variable: " + input.substring(currentPosition));
                }
            } else if ("[]".indexOf(currentChar) != -1) {
                tokens.add(new Token(TokenType.SYMBOL, String.valueOf(currentChar)));
                currentPosition++;
            } else if (Character.isLetter(currentChar)) {
                // Assuming identifier handling remains the same; adjust as necessary
                tokens.add(new Token(TokenType.IDENTIFIER, consumeIdentifier()));
            } else {
                throw new InvalidTokenException("Unknown token: " + currentChar);
            }
        }
//        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    // Utility methods for consuming tokens based on regex patterns
    private String consumeRegex(Pattern pattern) {
        Matcher matcher = pattern.matcher(input.substring(currentPosition));
        if (matcher.lookingAt()) {
            String match = matcher.group();
            currentPosition += match.length();
            return match;
        }
        return "";
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

