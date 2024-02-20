package slogo.model.commands.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;

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
//    TODO: implement clearScreen
//    NOTE: This method should clear the screen and move the turtle home as well
//    model.clearScreen();
  }
}
