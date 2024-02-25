package slogo.model.api.command.math;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import slogo.model.api.command.Command;
import slogo.model.api.command.math.BinaryMathCommand;
import slogo.model.api.command.math.BinaryMathCommand.OperationType;
import slogo.model.api.command.math.PiCommand;
import slogo.model.api.command.math.PowerCommand;
import slogo.model.api.command.math.RandomDoubleCommand;
import slogo.model.api.command.math.RandomRangeCommand;
import slogo.model.api.command.math.RemainderCommand;
import slogo.model.api.command.math.UnaryMathCommand;
import slogo.model.api.command.math.UnaryMathCommand.UnaryOperationType;

public class MathTest {

  private static final double TOLERANCE = 0.001;

  @DisplayName("Test Binary Commands")
  @Nested
  class TestBinaryCommand {

    @Test
    public void testSum() {
      Command sum = new BinaryMathCommand(5, 5, OperationType.SUM);
      double result = sum.execute();
      assertEquals(10, result, TOLERANCE);
    }

    @Test
    public void testDifference() {
      Command difference = new BinaryMathCommand(5, 5, OperationType.DIFFERENCE);
      double result = difference.execute();
      assertEquals(0, result, TOLERANCE);
    }

    @Test
    public void testProduct() {
      Command product = new BinaryMathCommand(5, 5, OperationType.PRODUCT);
      double result = product.execute();
      assertEquals(25, result, TOLERANCE);
    }

    @Test
    public void testQuotient() {
      Command quotient = new BinaryMathCommand(5, 5, OperationType.QUOTIENT);
      double result = quotient.execute();
      assertEquals(1, result, TOLERANCE);
    }
  }

  @DisplayName("Test PI Command")
  @Nested
  class TestPICommand {

    @Test
    public void testPI() {
      Command pi = new PiCommand();
      double result = pi.execute();
      assertEquals(Math.PI, result, TOLERANCE);
    }
  }

  @DisplayName("Test Power Command")
  @Nested
  class TestPowerCommand {

    @Test
    public void testPower() {
      Command power = new PowerCommand(2, 3);
      double result = power.execute();
      assertEquals(8, result, TOLERANCE);
    }
  }

  @DisplayName("Test Random Double Command")
  @Nested
  class TestRandomDoubleCommand {

    @Test
    public void testRandomDouble() {
      Command randomDouble = new RandomDoubleCommand(1);
      double result = randomDouble.execute();
      assertAll(
          () -> assertTrue(result >= 0),
          () -> assertTrue(result <= 1)
      );
    }
  }

  @DisplayName("Test Random RAnge Commands")
  @Nested
  class TestRandomRangeCommand {

    @Test
    public void testRandomRange() {
      Command randomRange = new RandomRangeCommand(5, 10);
      double result = randomRange.execute();
      assertAll(
          () -> assertTrue(result >= 5),
          () -> assertTrue(result <= 10)
      );
    }
  }

  @DisplayName("Test Remainder Command")
  @Nested
  class TestRemainderCommand {

    @Test
    public void testRemainder() {
      Command remainder = new RemainderCommand(5, 3);
      double result = remainder.execute();
      assertEquals(2, result, TOLERANCE);
    }
  }

  @DisplayName("Test Unary Math Command")
  @Nested
  class TestUnaryMathCommand {

    @Test
    public void testMinus() {
      Command minus = new UnaryMathCommand(5, UnaryOperationType.MINUS);
      double result = minus.execute();
      assertEquals(-5, result, TOLERANCE);
    }

    @Test
    public void testSine() {
      Command sine = new UnaryMathCommand(90, UnaryOperationType.SINE);
      double result = sine.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testCosine() {
      Command cosine = new UnaryMathCommand(0, UnaryOperationType.COSINE);
      double result = cosine.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testTangent() {
      Command tangent = new UnaryMathCommand(45, UnaryOperationType.TANGENT);
      double result = tangent.execute();
      assertEquals(1, result, TOLERANCE);
    }

    @Test
    public void testArctangent() {
      Command arctangent = new UnaryMathCommand(1, UnaryOperationType.ARCTANGENT);
      double result = arctangent.execute();
      assertEquals(45, result, TOLERANCE);
    }

    @Test
    public void testSquareRoot() {
      Command squareRoot = new UnaryMathCommand(4, UnaryOperationType.SQUAREROOT);
      double result = squareRoot.execute();
      assertEquals(2, result, TOLERANCE);
    }

    @Test
    public void testLog() {
      Command log = new UnaryMathCommand(10, UnaryOperationType.LOG);
      double result = log.execute();
      assertEquals(2.302585092994046, result, TOLERANCE);
    }
  }


}
