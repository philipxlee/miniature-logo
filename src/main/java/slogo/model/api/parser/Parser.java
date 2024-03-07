package slogo.model.api.parser;

import java.util.*;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.Command;
import slogo.model.api.command.CommandFactory;
import slogo.model.api.command.turtle.ForwardCommand;
import slogo.model.api.command.turtle.RotateCommand;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.nodes.ASTNode;
import slogo.model.api.parser.nodes.CommandNode;
import slogo.model.api.parser.nodes.ProcedureNode;
import slogo.model.api.parser.nodes.RepeatNode;

public class Parser {
  private final List<Token> tokens;
  private int current = 0;
  private final Map<String, Class<? extends Command>> commandMap = new HashMap<>();
  private ASTNode ast; // Instance variable for the AST

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
    initializeCommandMap();
  }

  private void initializeCommandMap() {
    commandMap.put("fd", ForwardCommand.class);
    commandMap.put("rt", RotateCommand.class);
    // Initialize commandMap with mappings from command strings to command classes
  }

  public void parse() {
    List<ASTNode> nodes = new ArrayList<>();
    while (!isAtEnd()) {
      nodes.add(parsePrimary());
    }
    // Directly set the instance variable instead of returning it
    this.ast = nodes.size() == 1 ? nodes.get(0) : new ProcedureNode("main", new ArrayList<>(), nodes);
  }

  private ASTNode parsePrimary() {
    Token token = advance();
    switch (token.getType()) {
      case IDENTIFIER:
        // !!! use the xml map here - the execution is downstream from this
        return new CommandNode(token.getValue(), parseArguments());
      case TO:
        return parseProcedure();
      case REPEAT:
        return parseRepeat();
      default:
        throw new RuntimeException("Unexpected token: " + token.getType());
    }
  }

  private List<Double> parseArguments() {
    List<Double> args = new ArrayList<>();
    while (match(TokenType.NUMBER)) {
      args.add(Double.parseDouble(advance().getValue()));
    }
    return args;
  }

  private ASTNode parseProcedure() {
    // Assuming the next tokens are the procedure name and parameters
    String name = advance().getValue(); // Get the procedure name
    List<String> parameters = new ArrayList<>();
    // Example: Assuming parameters are enclosed in '[' and ']'
    consume(TokenType.SYMBOL, "Expected '[' before parameters.");
    while (!check(TokenType.SYMBOL)) { // Assumes ']' ends the parameter list
      parameters.add(advance().getValue());
    }
    consume(TokenType.SYMBOL, "Expected ']' after parameters.");

    // Parse the body of the procedure
    List<ASTNode> body = new ArrayList<>();
    consume(TokenType.SYMBOL, "Expected '[' before body.");
    while (!check(TokenType.SYMBOL)) { // Assumes ']' ends the body
      body.add(parsePrimary());
    }
    consume(TokenType.SYMBOL, "Expected ']' after body.");

    return new ProcedureNode(name, parameters, body);
  }

  private ASTNode parseRepeat() {
    // Assuming the next token is the number of repetitions
    double times = Double.parseDouble(advance().getValue());
    List<ASTNode> body = new ArrayList<>();

    consume(TokenType.SYMBOL, "Expected '[' before repeat body.");
    while (!check(TokenType.SYMBOL)) { // Assumes ']' ends the repeat body
      body.add(parsePrimary());
    }
    consume(TokenType.SYMBOL, "Expected ']' after repeat body.");

    return new RepeatNode((int) times, body);
  }

  public void executeAST() throws Exception {
    executeNode(ast);
  }

  private void executeNode(ASTNode node) throws Exception {
    if (node instanceof CommandNode) {
      executeCommandNode((CommandNode) node);
    } else if (node instanceof ProcedureNode) {
      // Procedure execution logic
    } else if (node instanceof RepeatNode) {
      RepeatNode repeatNode = (RepeatNode) node;
      for (int i = 0; i < repeatNode.times; i++) {
        for (ASTNode child : repeatNode.body) {
          executeNode(child);
        }
      }
    }
    // Handle other node types as necessary
  }


  private boolean isAtEnd() {
    return current >= tokens.size();
  }

  private Token advance() {
    if (!isAtEnd()) current++;
    return tokens.get(current - 1);
  }

  private boolean check(TokenType type) {
    if (isAtEnd()) return false;
    return peek().getType() == type;
  }

  private Token peek() {
    return tokens.get(current);
  }

  private boolean match(TokenType... types) {
    for (TokenType type : types) {
      if (check(type)) {
        advance();
        return true;
      }
    }
    return false;
  }

  private Token consume(TokenType type, String message) {
    if (check(type)) {
      return advance();
    }
    throw new RuntimeException(message); // Consider a more sophisticated error handling approach
  }
}