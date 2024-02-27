package slogo.model.api.command.bool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import slogo.model.api.command.Command;
import slogo.model.api.command.bool.BinaryBoolCommand.OperationType;
import slogo.model.api.command.bool.UnaryBoolCommand.UnaryOperationType;

public class BoolTest {

  private static final double TOLERANCE = 0.001;

  @DisplayName("Test Binary Boolean Command")
  @Nested
  class TestBinaryBooleanCommand {

    @Test
    public void testAnd() {
      Command and = new BinaryBoolCommand(1, 1, OperationType.AND);
      double result = and.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testOr() {
      Command or = new BinaryBoolCommand(3, 4, OperationType.OR);
      double result = or.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testEqual() {
      Command equal = new BinaryBoolCommand(5, 5, OperationType.EQUAL);
      double result = equal.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testNotEqual() {
      Command notEqual = new BinaryBoolCommand(5, 5, OperationType.NOTEQUAL);
      double result = notEqual.execute();
      assertEquals(0, result, TOLERANCE);
    }

    @Test
    public void testLess() {
      Command less = new BinaryBoolCommand(5, 5, OperationType.LESS);
      double result = less.execute();
      assertEquals(0, result, TOLERANCE);
    }

    @Test
    public void testLessEqual() {
      Command lessEqual = new BinaryBoolCommand(5, 5, OperationType.LESSEQUAL);
      double result = lessEqual.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testGreater() {
      Command greater = new BinaryBoolCommand(5, 5, OperationType.GREATER);
      double result = greater.execute();
      assertEquals(0, result, TOLERANCE);
    }

    @Test
    public void testGreaterEqual() {
      Command greaterEqual = new BinaryBoolCommand(5, 5, OperationType.GREATEREQUAL);
      double result = greaterEqual.execute();
      assertEquals(1, result, TOLERANCE);
    }
  }

  @DisplayName("Test Unary Boolean Command")
  @Nested
  class TestUnaryBooleanCommand {

    @Test
    public void testNot() {
      Command not = new UnaryBoolCommand(0, UnaryOperationType.NOT);
      double result = not.execute();
      assertEquals(1, result, TOLERANCE);
    }
  }
}
