package slogo.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.Parser;

public class InvalidCommandExceptionTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  public void testExceptionMessage() {
    String expectedMessage = "Command string is invalid or empty.";
    Exception exception = assertThrows(InvalidCommandException.class, () -> {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      Parser parser = new Parser(turtleModel, lineModel);
      String commandString = "";
      parser.parseCommand(commandString);
    });

    assertEquals(expectedMessage, exception.getMessage());
  }

}
