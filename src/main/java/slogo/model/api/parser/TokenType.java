package slogo.model.api.parser;

// TokenType.java
public enum TokenType {
    TO, // the 'to' keyword
    NUMBER, // numeric literals
    VARIABLE, // variables starting with ':'
    SYMBOL, // symbols like '[', ']', etc.
    IDENTIFIER, // function names and other identifiers
    EOF, // end of file/input marker
    UNKNOWN // for unrecognized tokens
}

