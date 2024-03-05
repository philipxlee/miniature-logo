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
import slogo.model.api.data.VariablesModel;
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
    void testExecuteBasic() throws InvalidCommandException {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      VariablesModel variablesModel = new VariablesModel();
      CommandController commandController = new CommandController(turtleModel, lineModel,
          commandHistoryModel, variablesModel);
      commandController.executeCommand("fd 50");
      commandController.executeCommand("rt 90");
      assertAll(
          () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
          () -> assertEquals(0, turtleModel.getPositionY(), TOLERANCE)
      );
    }

    @Test
    void testExecuteDetailed() throws InvalidCommandException {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      VariablesModel variablesModel = new VariablesModel();
      CommandController commandController = new CommandController(turtleModel, lineModel,
          commandHistoryModel, variablesModel);
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
      VariablesModel variablesModel = new VariablesModel();
      CommandController commandController = new CommandController(turtleModel, lineModel,
          commandHistoryModel, variablesModel);
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
