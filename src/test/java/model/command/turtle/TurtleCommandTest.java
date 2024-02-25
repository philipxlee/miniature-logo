package model.command.turtle;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.command.turtle.*;

public class TurtleCommandTest {

  private static final double TOLERANCE = 0.001;

  @Test
  public void testClearScreen() {
    TurtleModel turtleModel = new TurtleModel(new LineModel());
    Command clearScreen = new ClearScreenCommand(turtleModel, new LineModel());
    double result = clearScreen.execute();
    assertAll(
        () -> assertEquals(result, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(result, turtleModel.getPositionY(), TOLERANCE),
        () -> assertEquals(result, turtleModel.getOrientation(), TOLERANCE)
    );
  }

  @Test
  public void testSetLocation() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command setLocation = new SetLocationCommand(turtleModel, 50, 50);
    double expectedDistance = Math.hypot(50 - 0, 50 - 0); // Distance from (0,0) to (50,50)
    double result = setLocation.execute();

    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(50, turtleModel.getPositionY(), TOLERANCE),
        () -> assertEquals(expectedDistance, result, TOLERANCE)
    );
  }

  @Test
  public void testRotate() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command rotate = new RotateCommand(turtleModel, 90);
    double result = rotate.execute();
    assertAll(
        () -> assertEquals(0, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(0, turtleModel.getPositionY(), TOLERANCE),
        () -> assertEquals(90, turtleModel.getOrientation(), TOLERANCE)
    );
  }

  @Test
  public void testMove() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command move = new ForwardCommand(turtleModel, 50);
    double result = move.execute();
    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(0, turtleModel.getPositionY(), TOLERANCE)
    );
  }

  @Test
  public void testPenUp() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command penUp = new PenCommand(turtleModel, false);
    penUp.execute();
    assertFalse(turtleModel.getPenDown());
  }

  @Test
  public void testPenDown() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command penDown = new PenCommand(turtleModel, true);
    penDown.execute();
    assertTrue(turtleModel.getPenDown());
  }

  @Test
  public void testPenToggle() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command penDown = new PenCommand(turtleModel, true);
    penDown.execute();
    assertTrue(turtleModel.getPenDown());
    Command penUp = new PenCommand(turtleModel, false);
    penUp.execute();
    assertFalse(turtleModel.getPenDown());
  }

  @Test
  public void testPenToggleTwice() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command penDown = new PenCommand(turtleModel, true);
    penDown.execute();
    assertTrue(turtleModel.getPenDown());
    Command penUp = new PenCommand(turtleModel, false);
    penUp.execute();
    assertFalse(turtleModel.getPenDown());
    penDown.execute();
    assertTrue(turtleModel.getPenDown());
  }

  @Test
  public void testTurtleVisibility() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command hideTurtle = new TurtleVisibleCommand(turtleModel, false);
    hideTurtle.execute();
    assertFalse(turtleModel.getVisible());
    Command showTurtle = new TurtleVisibleCommand(turtleModel, true);
    showTurtle.execute();
    assertTrue(turtleModel.getVisible());
  }

  @Test
  public void testSetOrientation() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command setOrientation = new SetOrientationCommand(turtleModel, 90);
    setOrientation.execute();
    assertEquals(90, turtleModel.getOrientation(), TOLERANCE);
  }

  @Test
  public void testSetOrientationWithNegativeAngle() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command setOrientation = new SetOrientationCommand(turtleModel, -90);
    setOrientation.execute();
    assertEquals(270, turtleModel.getOrientation(), TOLERANCE);
  }

  @Test
  public void testSetOrientationWithGivenXY() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command setOrientation = new SetOrientationCommand(turtleModel, 50, 50);
    setOrientation.execute();
    assertEquals(45, turtleModel.getOrientation(), TOLERANCE);
  }

}
