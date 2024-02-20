package slogo.model.commands;
import slogo.model.api.TurtleModel;

public class MoveForwardCommand implements Command {
  private TurtleModel model;
  private double distance;

  public MoveForwardCommand(TurtleModel model, double distance) {
    this.model = model;
    this.distance = distance;
  }

  @Override
  public void execute() {
    model.moveForward(distance);
    }
}