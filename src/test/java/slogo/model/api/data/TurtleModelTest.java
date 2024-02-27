package slogo.model.api.data;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TurtleModelTest tests the TurtleModel class.
 */
public class TurtleModelTest {

  private static final double TOLERANCE = 0.001;

  @BeforeEach
  void setUp() {
  }

  @Test
  void testMove() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    double result = turtleModel.moveTurtle(50);
    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(0, turtleModel.getPositionY(), TOLERANCE)
    );
  }

  @Test
  void testRotate() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.moveTurtle(50);
    turtleModel.rotateTurtle(90);
    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(0, turtleModel.getPositionY(), TOLERANCE),
        () -> assertEquals(90, turtleModel.getOrientation(), TOLERANCE)
    );
  }

  @Test
  void testMoveAndRotate() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.moveTurtle(50);
    turtleModel.rotateTurtle(90);
    turtleModel.moveTurtle(50);
    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(50, turtleModel.getPositionY(), TOLERANCE),
        () -> assertEquals(90, turtleModel.getOrientation(), TOLERANCE)
    );
  }

  @Test
  void testMoveAndRotateAndMove() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.moveTurtle(50);
    turtleModel.rotateTurtle(90);
    turtleModel.moveTurtle(50);
    turtleModel.rotateTurtle(90);
    turtleModel.moveTurtle(50);
    assertAll(
        () -> assertEquals(0, turtleModel.getPositionX(), TOLERANCE),
        () -> assertEquals(50, turtleModel.getPositionY(), TOLERANCE),
        () -> assertEquals(180, turtleModel.getOrientation(), TOLERANCE)
    );
  }

  @Test
  void testPenDown() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.setPenDown(true);
    turtleModel.moveTurtle(50);
    assertTrue(turtleModel.getPenDown());
  }

  @Test
  void testPenUp() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.setPenDown(false);
    assertFalse(turtleModel.getPenDown());
  }

  @Test
  void testSetOrientation() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.setOrientation(90);
    assertEquals(90, turtleModel.getOrientation());
  }

  @Test
  void testSetPenDown() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    turtleModel.setPenDown(true);
    assertTrue(turtleModel.getPenDown());
  }

  @Test
  void testGetPositionX() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    assertEquals(0, turtleModel.getPositionX());
  }

  @Test
  void testGetPositionY() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    assertEquals(0, turtleModel.getPositionY());
  }

  @Test
  void testGetOrientation() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    assertEquals(0, turtleModel.getOrientation());
  }

  @Test
  void testGetPenDown() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    assertFalse(turtleModel.getPenDown());
  }
}
