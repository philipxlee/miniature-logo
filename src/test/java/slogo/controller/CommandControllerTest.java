package controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import slogo.controller.CommandController;
import slogo.exceptions.InvalidCommandException;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.exceptions.InvalidTokenException;
import slogo.view.scenes.main.TurtlePane;


public class CommandControllerTest {

  private static final double TOLERANCE = 0.001;

  @BeforeEach
  void setUp() {
  }

  @Nested
  @DisplayName("Test executeCommand")
  class ExecuteCommandTests {

    @Test
    void testExecuteBasic() throws Exception, InvalidTokenException {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      CommandController commandController = new CommandController(turtleModel, lineModel,
          commandHistoryModel);
      commandController.executeCommand("fd 50");
      commandController.executeCommand("rt 90");
      assertAll(
          () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
          () -> assertEquals(0, turtleModel.getPositionY(), TOLERANCE)
      );
    }

    @Test
    void testExecuteDetailed() throws Exception, InvalidTokenException {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      CommandController commandController = new CommandController(turtleModel, lineModel,
          commandHistoryModel);
      commandController.executeCommand("fd 50");
      commandController.executeCommand("fd 100");
      commandController.executeCommand("rt 90");
      commandController.executeCommand("bk 100");
      commandController.executeCommand("rt 90");
      commandController.executeCommand("fd 100");
      assertAll(
          () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
          () -> assertEquals(-100, turtleModel.getPositionY(), TOLERANCE)
      );
    }

    @Test
    void testSubscription() {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      CommandController commandController = new CommandController(turtleModel, lineModel,
          commandHistoryModel);
      TurtlePane turtlePane = new TurtlePane(200, 200);
      commandController.observeTurtle(turtlePane);
      commandController.observeLines(turtlePane);
      commandController.observeHistory(turtlePane);
      assertAll(
          () -> assertEquals(commandController.getClass(), CommandController.class)
      );
    }
  }
}
