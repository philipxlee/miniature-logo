package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class MoveCommand implements Command {

  private final TurtleModel model;
  private final double distance;

  /**
   * Constructor for the MoveCommand. Takes in a model and a distance to move the turtle either
   * forward or backward by the given distance.
   *
   * @param model    the model to change
   * @param distance the distance to move the turtle by
   */
  public MoveCommand(TurtleModel model, double distance) {
    this.model = model;
    this.distance = distance;
  }

  /**
   * Executes the move turtle command. Moves the turtle forward by the given distance, or backwards
   * if the distance is negative.
   */
  @Override
  public void execute() {
    model.moveTurtle(distance);
  }

}