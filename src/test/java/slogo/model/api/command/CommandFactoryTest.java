package slogo.model.api.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.exceptions.InvalidCommandException;
import slogo.model.api.command.turtle.ClearScreenCommand;
import slogo.model.api.command.turtle.ForwardCommand;
import slogo.model.api.command.turtle.RotateCommand;
import slogo.model.api.command.turtle.SetLocationCommand;
import slogo.model.api.command.turtle.SetOrientationCommand;
import slogo.model.api.command.turtle.TurtleVisibleCommand;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;

public class CommandFactoryTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void testCommandFactoryBasic() throws InvalidCommandException {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    CommandFactory commandFactory = new CommandFactory(turtleModel, lineModel);
    String commandString = "fd";
    double[] params = new double[]{50.0};
    Command command = commandFactory.createCommand(commandString, params[0]);
    assertEquals(command.getClass(), ForwardCommand.class);
  }

  @Test
  void testCommandFactoryDetailed() throws InvalidCommandException {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    CommandFactory commandFactory = new CommandFactory(turtleModel, lineModel);
    String[] commands = new String[]{"fd", "rt", "bk", "cs", "home", "fd", "ht", "seth"};
    double[] params = new double[]{50.0, 90.0, 30.0, Double.MAX_VALUE, Double.MAX_VALUE, 10.0,
        Double.MAX_VALUE, 39.0};
    Class[] result = new Class[]{ForwardCommand.class, RotateCommand.class, ForwardCommand.class,
        ClearScreenCommand.class, SetLocationCommand.class, ForwardCommand.class,
        TurtleVisibleCommand.class, SetOrientationCommand.class};
    for (int i = 0; i < commands.length; i++) {
      Command command = commandFactory.createCommand(commands[i], params[i]);
      assertEquals(command.getClass(), result[i]);
    }
  }
}
