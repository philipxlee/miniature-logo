package slogo.model.command.bool;

import slogo.model.command.Command;

/**
 * BinaryBoolCommand represents a binary boolean operation.
 */
public class BinaryBoolCommand implements Command {

  private static final double TOLERANCE = 0.001;
  private final double operand1;
  private final double operand2;
  private final OperationType operationType;

  /**
   * Constructor for the BinaryBoolCommand, which performs a binary boolean operation.
   *
   * @param operand1      the first operand
   * @param operand2      the second operand
   * @param operationType the type of operation to perform
   */
  public BinaryBoolCommand(double operand1, double operand2, OperationType operationType) {
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operationType = operationType;
  }

  /**
   * Executes the binary boolean command.
   *
   * @return the result of the operation
   * @throws IllegalStateException if the operation type is not recognized
   */
  @Override
  public Double execute() {
    return switch (operationType) {
      case EQUAL -> evaluateEqual();
      case LESS -> evaluateLess();
      case GREATER -> evaluateGreater();
      case AND -> evaluateAnd();
      case OR -> evaluateOr();
      case NOTEQUAL -> evaluateNotEqual();
      case LESSEQUAL -> evaluateLessEqual();
      case GREATEREQUAL -> evaluateGreaterEqual();
      default -> throw new IllegalStateException("Unexpected operation type: " + operationType);
    };
  }

  private double evaluateEqual() {
    return areEqual(operand1, operand2) ? 1.0 : 0.0;
  }

  private double evaluateLess() {
    return (operand1 < operand2 && !areEqual(operand1, operand2)) ? 1.0 : 0.0;
  }

  private double evaluateGreater() {
    return (operand1 > operand2 && !areEqual(operand1, operand2)) ? 1.0 : 0.0;
  }

  private double evaluateAnd() {
    return (operand1 != 0 && operand2 != 0) ? 1.0 : 0.0;
  }

  private double evaluateOr() {
    return (operand1 != 0 || operand2 != 0) ? 1.0 : 0.0;
  }

  private double evaluateNotEqual() {
    return !areEqual(operand1, operand2) ? 1.0 : 0.0;
  }

  private double evaluateLessEqual() {
    return (operand1 <= operand2 || areEqual(operand1, operand2)) ? 1.0 : 0.0;
  }

  private double evaluateGreaterEqual() {
    return (operand1 >= operand2 || areEqual(operand1, operand2)) ? 1.0 : 0.0;
  }

  private boolean areEqual(double a, double b) {
    return Math.abs(a - b) < TOLERANCE;
  }

  /**
   * Define types for binary boolean operations.
   */
  public enum OperationType {
    EQUAL, LESS, GREATER, AND, OR, NOTEQUAL, LESSEQUAL, GREATEREQUAL
  }
}
