package slogo.model.command.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;

public class TurtleVisibleCommand implements Command {

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
  public void execute() {
    model.setVisible(show);
  }
}
