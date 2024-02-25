package slogo.model.command.math;

import slogo.model.command.Command;

public class UnaryMathCommand implements Command {
  public enum OperationType {
    MINUS, SINE, COSINE, TANGENT, ARCTANGENT, SQUAREROOT, LOG
  }

  private final double operand;
  private final OperationType operationType;

  public UnaryMathCommand(double operand, OperationType operationType) {
    this.operand = operand;
    this.operationType = operationType;
  }

  @Override
  public void execute() {
    switch (operationType) {
        case MINUS -> System.out.println(-operand); // placeholder
//      case MINUS:
//        return -operand;
//      case SINE:
//        return Math.sin(Math.toRadians(operand));
//      case COSINE:
//        return Math.cos(Math.toRadians(operand));
//      case TANGENT:
//        return Math.tan(Math.toRadians(operand));
//      case ARCTANGENT:
//        return Math.toDegrees(Math.atan(operand));
//      case SQUAREROOT:
//        return Math.sqrt(operand);
//      case LOG:
//        return Math.log(operand);
      default ->
        throw new IllegalStateException("Unexpected operation type: " + operationType);
    }
  }
}
