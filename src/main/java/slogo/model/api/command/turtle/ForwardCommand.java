package slogo.model.api.command.turtle;

import java.util.List;
import slogo.model.api.command.Command;
import slogo.model.api.data.TurtleModel;
import slogo.model.api.parser.nodes.ConstantNode;

/**
 * ForwardCommand moves the Turtle forward.
 */
public class ForwardCommand implements Command {

  private final TurtleModel model;
  private final double distance;

  /**
   * Constructor for the MoveCommand. Takes in a model and a distance to move the turtle either
   * forward or backward by the given distance.
   *
   * @param model    the model to change
   * @param params the distance to move the turtle by
   */
  public ForwardCommand(TurtleModel model, List<Object> params) {
    this.model = model;
    this.distance = ((ConstantNode)params.get(0)).getValue();
  }

  /**
   * Executes the move turtle command. Moves the turtle forward by the given distance, or backwards
   * if the distance is negative.
   */
  @Override
  public Double execute() {
    return model.moveTurtle(distance);
  }
}