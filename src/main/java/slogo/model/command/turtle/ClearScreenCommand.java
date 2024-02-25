package slogo.model.command.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;

public class ClearScreenCommand implements Command {

  private final TurtleModel model;

  /**
   * Constructor for the ClearScreenCommand, which clears the screen and moves the turtle home
   *
   * @param model the model to change
   */
  public ClearScreenCommand(TurtleModel model) {
    this.model = model;
  }

  @Override
  public void execute() {
    model.clearScreen();
  }
}
