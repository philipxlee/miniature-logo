package slogo.model.command.bool;

import slogo.model.command.Command;

public class BinaryBoolCommand implements Command {
  public enum OperationType {
    EQUAL, LESS, GREATER, AND, OR, NOTEQUAL, LESSEQUAL, GREATEREQUAL
  }

  private static final double TOLERANCE = 0.001;
  private final double operand1;
  private final double operand2;
  private final OperationType operationType;

  public BinaryBoolCommand(double operand1, double operand2, OperationType operationType) {
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operationType = operationType;
  }

  private static boolean areEqual(double a, double b) {
    return Math.abs(a - b) < TOLERANCE;
  }

  @Override
  public void execute() { // change to double
    switch (operationType) {
      case EQUAL -> System.out.println(areEqual(operand1, operand2) ? 1.0 : 0.0); // placeholder
//      case EQUAL:
//        return areEqual(operand1, operand2) ? 1.0 : 0.0;
//      case LESS:
//        return (operand1 < operand2 && !areEqual(operand1, operand2)) ? 1.0 : 0.0;
//      case GREATER:
//        return (operand1 > operand2 && !areEqual(operand1, operand2)) ? 1.0 : 0.0;
//      case AND:
//        return (operand1 != 0 && operand2 != 0) ? 1.0 : 0.0;
//      case OR:
//        return (operand1 != 0 || operand2 != 0) ? 1.0 : 0.0;
//      case NOTEQUAL:
//        return !areEqual(operand1, operand2) ? 1.0 : 0.0;
//      case LESSEQUAL:
//        return (operand1 <= operand2 || areEqual(operand1, operand2)) ? 1.0 : 0.0;
//      case GREATEREQUAL:
//        return (operand1 >= operand2 || areEqual(operand1, operand2)) ? 1.0 : 0.0;
      default ->
        throw new IllegalStateException("Unexpected operation type: " + operationType);
    }
  }
}
