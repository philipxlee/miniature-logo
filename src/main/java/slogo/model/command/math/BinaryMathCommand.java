package slogo.model.command.math;

import slogo.model.command.Command;

public class BinaryMathCommand implements Command {
  public enum OperationType {
    SUM, DIFFERENCE, PRODUCT, QUOTIENT
  }

  private final double operand1;
  private final double operand2;
  private final OperationType operationType;

  public BinaryMathCommand(double operand1, double operand2, OperationType operationType) {
    this.operand1 = operand1;
    this.operand2 = operand2;
    this.operationType = operationType;
  }

  @Override
  public void execute() { // change to double.. F***!!!!
    switch (operationType) {
      case SUM -> System.out.println(operand1 + operand2); // placeholder
//      case SUM:
//        return operand1 + operand2;
//      case DIFFERENCE:
//        return operand1 - operand2;
//      case PRODUCT:
//        return operand1 * operand2;
//      case QUOTIENT:
//        return operand1 / operand2;
      default -> throw new IllegalStateException("Unexpected operation type: " + operationType);
    }
  }
}
