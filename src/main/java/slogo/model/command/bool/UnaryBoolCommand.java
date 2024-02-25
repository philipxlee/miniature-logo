package slogo.model.command.bool;

import slogo.model.command.Command;

public class UnaryBoolCommand implements Command {

  private final double operand;
  private final OperationType operationType;
  public UnaryBoolCommand(double operand, OperationType operationType) {
    this.operand = operand;
    this.operationType = operationType;
  }

  @Override
  public void execute() { // change to double
    if (operationType == OperationType.NOT) {
//      return operand == 0 ? 1.0 : 0.0;
    }
    throw new IllegalStateException("Unexpected operation type: " + operationType);
  }

  public enum OperationType {
    NOT
  }
}
