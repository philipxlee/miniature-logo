package controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import slogo.controller.CommandController;
import slogo.model.api.CommandHistoryModel;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;


public class CommandControllerTest {

  @BeforeEach
  void setUp() {
  }

  @Nested
  @DisplayName("Test executeCommand")
  class ExecuteCommandTests {

    @Test
    void testExecuteCommand() {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      CommandController control = new CommandController(turtleModel, commandHistoryModel);
      control.executeCommand("fd 50");
      control.executeCommand("rt 90");
      assertAll(
          () -> assertEquals(50, turtleModel.getX()),
          () -> assertEquals(0, turtleModel.getY())
      );
    }

    @Test
    void testExecuteCommandAndRotate() {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      CommandController control = new CommandController(turtleModel, commandHistoryModel);
      control.executeCommand("fd 50");
      control.executeCommand("rt 90");
      assertAll(
          () -> assertEquals(50, turtleModel.getX()),
          () -> assertEquals(0, turtleModel.getY()),
          () -> assertEquals(90, turtleModel.getOrientation())
      );
    }

    @Test
    void testExecuteCommandAndRotateAndMove() {
      LineModel lineModel = new LineModel();
      TurtleModel turtleModel = new TurtleModel(lineModel);
      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
      CommandController control = new CommandController(turtleModel, commandHistoryModel);
      control.executeCommand("fd 50");
      control.executeCommand("rt 90");
      control.executeCommand("fd 50");
      assertAll(
          () -> assertEquals(50, turtleModel.getX()),
          () -> assertEquals(50, turtleModel.getY()),
          () -> assertEquals(90, turtleModel.getOrientation())
      );
    }
  }
}
