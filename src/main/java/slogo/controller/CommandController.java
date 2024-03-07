package slogo.controller;

import slogo.exceptions.InvalidCommandException;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.ParserExecutor;
import slogo.model.api.parser.exceptions.InvalidTokenException;
import slogo.model.api.parser.metadata.CommandMetadata;
import slogo.model.api.parser.metadata.CommandMetadataLoader;
import slogo.observer.Observer;
import slogo.model.api.parser.Tokenizer;
import slogo.model.api.parser.Token;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * CommandController handles user input sent from the view.
 */
public class CommandController {

  private final TurtleModel turtleModel;
  private final LineModel lineModel;
  private final CommandHistoryModel commandHistoryModel;
  // private final Parser parser;
  private final Tokenizer tokenizer;
  private final CommandMetadataLoader metadataLoader;

  /**
   * CommandController constructor initializes new parser.
   *
   * @param turtleModel         Turtle Model used for commands
   * @param lineModel           LineModel used for lines
   * @param commandHistoryModel Command History Model used for command history
   */
  public CommandController(TurtleModel turtleModel, LineModel lineModel,
      CommandHistoryModel commandHistoryModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
    this.commandHistoryModel = commandHistoryModel;
    // this.parser = new Parser(turtleModel, lineModel);
    this.metadataLoader = new CommandMetadataLoader();
    metadataLoader.loadAllCommandMetadata();
    this.tokenizer = new Tokenizer();
  }

  /**
   * Executes the command by parsing the command and executing it. Example: "fd 50" to move the
   * turtle forward 50 pixels. The command is parsed and executed using the CommandParser class
   *
   * @param commandString the command to be executed as a string
   */
  public void executeCommand(String commandString) throws Exception, InvalidTokenException {
    tokenizer.setInput(commandString);
    // should there be additional error handling here
    List<Token> tokens = tokenizer.tokenize();
    System.out.println(Arrays.toString(tokens.toArray()));
    Map<String, CommandMetadata> commandMetadataMap = metadataLoader.getAllCommandMetadata();

    ParserExecutor parser = new ParserExecutor(tokens, commandMetadataMap, turtleModel, lineModel);
    parser.parse();

    // and here
    parser.executeAST();

    commandHistoryModel.addCommand(commandString);
  }

//  /**
//   * Sets the input text in the parser.
//   *
//   * @param text the input text to set
//   */
//  public void setInputText(String text) {
//    executeCommand(text);
//  }

  /**
   * Subscribe to updates from the TurtleModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeTurtle(Observer observer) {
    turtleModel.addObserver(observer);
  }

  /**
   * Subscribe to updates from the CommandHistoryModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeHistory(Observer observer) {
    commandHistoryModel.addObserver(observer);
  }

  /**
   * Subscribe to updates from the LineModel.
   *
   * @param observer that wants to subscribe
   */
  public void observeLines(Observer observer) {
    lineModel.addObserver(observer);
  }
}
