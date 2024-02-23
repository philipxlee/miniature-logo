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
    TurtleModel turtle = new TurtleModel();
    turtle.moveTurtle(50);
    assertAll(
        () -> assertEquals(50, turtle.getX()),
        () -> assertEquals(0, turtle.getY())
    );
  }

  @Test
  void testRotate() {
    TurtleModel turtle = new TurtleModel();
    turtle.rotate(90);
    assertEquals(90, turtle.getOrientation());
  }

  @Test
  void testMoveTurtleAndRotate() {
    TurtleModel turtle = new TurtleModel();
    turtle.moveTurtle(50);
    turtle.rotate(90);
    assertAll(
        () -> assertEquals(50, turtle.getX()),
        () -> assertEquals(0, turtle.getY()),
        () -> assertEquals(90, turtle.getOrientation())
    );
  }
}
