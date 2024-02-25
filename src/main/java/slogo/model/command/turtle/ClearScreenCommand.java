package slogo.model.command.turtle;

import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;
import slogo.model.command.Command;

public class ClearScreenCommand implements Command {

  private final TurtleModel turtleModel;
  private final LineModel lineModel;

  /**
   * Constructor for the ClearScreenCommand, which clears the screen and moves the turtle home
   *
   * @param turtleModel the turtleModel to reset
   * @param lineModel:  the lineModel to reset
   */
  public ClearScreenCommand(TurtleModel turtleModel, LineModel lineModel) {
    this.turtleModel = turtleModel;
    this.lineModel = lineModel;
  }

  @Override
  public void execute() {
    turtleModel.setLocation(0.0, 0.0);
    turtleModel.setOrientation(0.0);
    lineModel.clearLines();
  }
}
