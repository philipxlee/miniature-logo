//package controller;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import slogo.controller.CommandController;
//import slogo.model.api.data.CommandHistoryModel;
//import slogo.model.api.data.LineModel;
//import slogo.model.api.data.TurtleModel;
//
//
//public class CommandControllerTest {
//
//  @BeforeEach
//  void setUp() {
//  }
//
//  @Nested
//  @DisplayName("Test executeCommand")
//  class ExecuteCommandTests {
//
//    @Test
//    void testExecuteCommand() {
//      LineModel lineModel = new LineModel();
//      TurtleModel turtleModel = new TurtleModel(lineModel);
//      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
////      CommandController control = new CommandController(turtleModel, commandHistoryModel,
////          lineModel);
////      control.executeCommand("fd 50");
////      control.executeCommand("rt 90");
//      assertAll(
//          () -> assertEquals(50, turtleModel.getPositionX()),
//          () -> assertEquals(0, turtleModel.getPositionY())
//      );
//    }
//
//    @Test
//    void testExecuteCommandAndRotate() {
//      LineModel lineModel = new LineModel();
//      TurtleModel turtleModel = new TurtleModel(lineModel);
//      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
////      CommandController control = new CommandController(turtleModel, commandHistoryModel,
////          lineModel);
////      control.executeCommand("fd 50");
////      control.executeCommand("rt 90");
//      assertAll(
//          () -> assertEquals(50, turtleModel.getPositionX()),
//          () -> assertEquals(0, turtleModel.getPositionY()),
//          () -> assertEquals(90, turtleModel.getOrientation())
//      );
//    }
//
//    @Test
//    void testExecuteCommandAndRotateAndMove() {
//      LineModel lineModel = new LineModel();
//      TurtleModel turtleModel = new TurtleModel(lineModel);
//      CommandHistoryModel commandHistoryModel = new CommandHistoryModel();
////      CommandController control = new CommandController(turtleModel, commandHistoryModel,
////          lineModel);
////      control.executeCommand("fd 50");
////      control.executeCommand("rt 90");
////      control.executeCommand("fd 50");
//      assertAll(
//          () -> assertEquals(50, turtleModel.getPositionX()),
//          () -> assertEquals(50, turtleModel.getPositionY()),
//          () -> assertEquals(90, turtleModel.getOrientation())
//      );
//    }
//
//
//  }
//}
