package slogo.model.api.command.turtle;

import slogo.model.api.command.Command;
import slogo.model.api.data.TurtleModel;

/**
 * TurtleVisibleCommand sets the visibility of the turtle.
 */
public class TurtleVisibleCommand extends Command {

  private final TurtleModel model;
  private final boolean show;

  /**
   * Constructs a TurtleVisibilityCommand.
   *
   * @param model the TurtleModel to affect
   * @param show  true to show the turtle, false to hide it
   */
  public TurtleVisibleCommand(TurtleModel model, boolean show) {
    this.model = model;
    this.show = show;
  }

  /**
   * Executes the turtle visibility command. Shows or hides the turtle.
   */
  @Override
  public Double execute() {
    model.setVisible(show);
    return show ? 1.0 : 0.0;
  }
}
