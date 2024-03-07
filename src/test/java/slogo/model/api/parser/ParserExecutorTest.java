package slogo.model.api.parser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.Command;
import slogo.model.api.command.turtle.ClearScreenCommand;
import slogo.model.api.command.turtle.ForwardCommand;
import slogo.model.api.command.turtle.PenCommand;
import slogo.model.api.command.turtle.RotateCommand;
import slogo.model.api.command.turtle.SetLocationCommand;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;

public class ParserExecutorTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void testForwardParse() throws InvalidCommandException {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    ParserExecutor parser = new ParserExecutor(turtleModel, lineModel);
    Command command = parser.parseCommand("fd 50");
    assertEquals(command.getClass(), ForwardCommand.class);
  }

  @Test
  void testRotateParse() throws InvalidCommandException {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    ParserExecutor parser = new ParserExecutor(turtleModel, lineModel);
    Command command = parser.parseCommand("rt 90");
    assertEquals(command.getClass(), RotateCommand.class);
  }

  @Test
  void testParse() throws InvalidCommandException {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    ParserExecutor parser = new ParserExecutor(turtleModel, lineModel);
    Command fd = parser.parseCommand("fd 50");
    Command bk = parser.parseCommand("bk 50");
    Command lt = parser.parseCommand("lt 50");
    Command rt = parser.parseCommand("rt 90");
    Command pd = parser.parseCommand("pd");
    Command pu = parser.parseCommand("pu");
    Command home = parser.parseCommand("home");
    Command cs = parser.parseCommand("cs");
    assertAll(
        () -> assertEquals(fd.getClass(), ForwardCommand.class),
        () -> assertEquals(bk.getClass(), ForwardCommand.class),
        () -> assertEquals(lt.getClass(), RotateCommand.class),
        () -> assertEquals(rt.getClass(), RotateCommand.class),
        () -> assertEquals(pd.getClass(), PenCommand.class),
        () -> assertEquals(pu.getClass(), PenCommand.class),
        () -> assertEquals(home.getClass(), SetLocationCommand.class),
        () -> assertEquals(cs.getClass(), ClearScreenCommand.class)
    );
  }
}
