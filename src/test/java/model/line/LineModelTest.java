package model.line;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import slogo.model.api.data.LineModel;
import slogo.model.line.Line;

public class LineModelTest {

  private static final double TOLERANCE = 0.001;

  @BeforeEach
  void setUp() {
  }


  @Test
  @DisplayName("Ensure Line record stores and retrieves values accurately")
  void testLineValues() {
    double startX = 1.0;
    double startY = 2.0;
    double endX = 3.0;
    double endY = 4.0;

    Line line = new Line(startX, startY, endX, endY);

    assertAll(
        () -> assertEquals(startX, line.startX(), "The startX should match the value passed to the constructor."),
        () -> assertEquals(startY, line.startY(), "The startY should match the value passed to the constructor."),
        () -> assertEquals(endX, line.endX(), "The endX should match the value passed to the constructor."),
        () -> assertEquals(endY, line.endY(), "The endY should match the value passed to the constructor.")
    );
  }

  @Test
  void testAddLine() {
    LineModel lineModel = new LineModel();
    Line line = new Line(0, 0, 50, 50);
    lineModel.addLine(line);
    assertTrue(lineModel.iterator().hasNext());
  }

  @Test
  void testClearLines() {
    LineModel lineModel = new LineModel();
    Line line = new Line(0, 0, 50, 50);
    lineModel.addLine(line);
    lineModel.clearLines();
    assertFalse(lineModel.iterator().hasNext());
  }

  @Test
  void testIterator() {
    LineModel lineModel = new LineModel();
    Line line = new Line(0, 0, 50, 50);
    lineModel.addLine(line);
    assertTrue(lineModel.iterator().hasNext());
  }


}
