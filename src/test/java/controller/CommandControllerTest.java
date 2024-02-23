package controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import slogo.controller.CommandController;


public class CommandControllerTest {

  @BeforeEach
  void setUp() {}


  @Nested
  @DisplayName("Test executeCommand")
  class ExecuteCommandTests {

    @Test
    void testExecuteCommand() {
      CommandController control = new CommandController();
      control.executeCommand("fd 50");
      control.executeCommand("rt 90");
      assertAll(
          () -> assertEquals(50, control.getTurtleModel().getX()),
          () -> assertEquals(0, control.getTurtleModel().getY())
      );
    }

    @Test
    void testExecuteCommandAndRotate() {
      CommandController control = new CommandController();
      control.executeCommand("fd 50");
      control.executeCommand("rt 90");
      assertAll(
          () -> assertEquals(50, control.getTurtleModel().getX()),
          () -> assertEquals(0, control.getTurtleModel().getY()),
          () -> assertEquals(90, control.getTurtleModel().getOrientation())
      );
    }

    @Test
    void testExecuteCommandAndRotateAndMove() {
      CommandController control = new CommandController();
      control.executeCommand("fd 50");
      control.executeCommand("rt 90");
      control.executeCommand("fd 50");
      assertAll(
          () -> assertEquals(50, control.getTurtleModel().getX()),
          () -> assertEquals(50, control.getTurtleModel().getY()),
          () -> assertEquals(90, control.getTurtleModel().getOrientation())
      );
    }
  }
}
