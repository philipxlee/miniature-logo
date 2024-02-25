package model.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.api.TurtleModel;

public class TurtleModelTest {

  @BeforeEach
  void setUp() {}

  @Test
  void testMoveTurtle() {
    TurtleModel turtleModel = new TurtleModel();
    turtleModel.moveTurtle(50);
    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX()),
        () -> assertEquals(0, turtleModel.getPositionY())
    );
  }

  @Test
  void testRotate() {
    TurtleModel turtleModel = new TurtleModel();
    turtleModel.rotateTurtle(90);
    assertEquals(90, turtleModel.getOrientation());
  }

  @Test
  void testMoveTurtleAndRotate() {
    TurtleModel turtleModel = new TurtleModel();
    turtleModel.moveTurtle(50);
    turtleModel.rotateTurtle(90);
    assertAll(
        () -> assertEquals(50, turtleModel.getPositionX()),
        () -> assertEquals(0, turtleModel.getPositionY()),
        () -> assertEquals(90, turtleModel.getOrientation())
    );
  }
}
