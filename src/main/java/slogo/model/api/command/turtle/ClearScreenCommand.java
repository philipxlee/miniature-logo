package slogo.model.api.command.turtle;

import slogo.model.api.command.Command;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;

/**
 * ClearScreenCommand clears the screen and resets the turtle's position.
 */
public class ClearScreenCommand extends Command {

  private final TurtleModel turtleModel;
  private final LineModel lineModel;

  /**
   * Constructor for the ClearScreenCommand, which clears the screen and moves the turtle home.
   *
   * @param turtleModel the turtleModel to reset
   * @param lineModel   the lineModel to reset
   */
  public ClearScreenCommand(TurtleModel turtleModel, LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
  }

  /**
   * Sets orientation to 0, position of turtle to 0 and clear lines.
   *
   * @return double representing the distance from the old position to home.
   */
  @Override
  public Double execute() {
    Double result = turtleModel.setLocation(0.0, 0.0);
    turtleModel.setOrientation(0.0);
    lineModel.clearLines();
    return result;
  }
}
