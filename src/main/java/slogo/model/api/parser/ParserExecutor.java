package slogo.model.api.parser;

import java.lang.reflect.Constructor;
import java.util.*;

import slogo.model.api.command.Command;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.metadata.CommandMetadata;
import slogo.model.api.parser.nodes.*;

public class ParserExecutor {
  private final List<Token> tokens;
  private Map<String, CommandMetadata> commandMetadataMap;
  private final TurtleModel turtleModel; // make sure to implement logic for multiple turtles
  private final LineModel lineModel;
  private int current = 0;
  private ASTNode ast; // Instance variable for the AST

  public ParserExecutor(List<Token> tokens, Map<String, CommandMetadata> commandMetadataMap, TurtleModel turtleModel, LineModel lineModel) {
    this.tokens = tokens;
    this.commandMetadataMap= commandMetadataMap;
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
  }

  public void parse() {
    while (!isAtEnd()) {
      ASTNode expr = parseExpression();
      ast.addChild(expr);
    }
  }

  private ASTNode parseExpression() {
    if (match(TokenType.NUMBER)) {
      return new ConstantNode(Double.parseDouble(previous().getValue()));
    } else if (match(TokenType.VARIABLE)) {
      return new VariableNode(previous().getValue().substring(1)); // Remove the ':' prefix
    } else if (match(TokenType.IDENTIFIER)) {
      return parseCommandOrProcedureCall();
    } else if (match(TokenType.SYMBOL) && previous().getValue().equals("[")) {
      return parseListOrBlock();
    } else {
      throw new RuntimeException("Unexpected expression start.");
    }
  }

  private ASTNode parseCommandOrProcedureCall() {
    String identifier = previous().getValue();
    CommandMetadata metadata = commandMetadataMap.get(identifier.toLowerCase());

    if (metadata == null) {
      throw new RuntimeException("Unknown command or procedure: " + identifier);
    }

    List<Object> arguments = new ArrayList<>();
    for (int i = 0; i < metadata.getParametersCount(); i++) {
      arguments.add(parseExpression()); // Recursively parse each argument
    }

    return new CommandNode(identifier, arguments);
  }

  private ASTNode parseListOrBlock() {
    List<ASTNode> nodes = new ArrayList<>();
    while (!check(TokenType.SYMBOL) || !peek().getValue().equals("]")) {
      if (isAtEnd()) throw new RuntimeException("Unterminated list or block.");
      nodes.add(parseExpression());
    }
    // Consume the closing ']'
    advance();

    return new ListNode(nodes); // Assuming ListNode is a type of ASTNode that can hold a list of other ASTNodes
  }

  public void executeAST() throws Exception {
    // Example of executing the root node of the AST
    executeNode(ast);
  }

  public Double executeNode(ASTNode node) throws Exception {
    if (node instanceof CommandNode) {
      CommandNode commandNode = (CommandNode) node;
      CommandMetadata metadata = commandMetadataMap.get(commandNode.getCommandName().toLowerCase());
      if (metadata == null) {
        throw new RuntimeException("Unknown command: " + commandNode.getCommandName());
      }

      Class<?> commandClass = Class.forName(metadata.getImplementingClass());
      Constructor<?> constructor = commandClass.getConstructor(TurtleModel.class, Double.class);

      Command command = (Command) constructor.newInstance(turtleModel, commandNode.getArguments());
      return command.execute();
    } else if (node instanceof VariableNode) {
      // Handle variable nodes, potentially updating or retrieving values from 'context'
    } else if (node instanceof ProcedureNode) {
      // Handle procedure nodes, potentially executing a series of child commands
    }

    // Recursively handle child nodes if applicable
    for (ASTNode child : node.getChildren()) {
      executeNode(child);
    }

    return null; // Or appropriate return value
  }

  // Assuming we have a method to consume and check for expected tokens
  private Token consume(TokenType type, String errorMessage) {
    if (check(type)) return advance();
    throw new RuntimeException(errorMessage);
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

  private boolean check(TokenType type) {
    if (isAtEnd()) return false;
    return peek().getType() == type;
  }

  private Token advance() {
    if (!isAtEnd()) current++;
    return previous();
  }

  private boolean isAtEnd() {
    return current >= tokens.size();
  }

  private Token peek() {
    return tokens.get(current);
  }

  private Token previous() {
    return tokens.get(current - 1);
  }
}