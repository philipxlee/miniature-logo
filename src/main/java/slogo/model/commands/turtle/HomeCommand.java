package slogo.model.commands.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;

public class HomeCommand implements Command {

  private final TurtleModel model;

  /**
   * Constructor for the HomeCommand, which moves the turtle to the home position
   *
   * @param model the model to change
   */
  public HomeCommand(TurtleModel model) {
    this.model = model;
  }

  /**
   * Executes the home command Moves the turtle to the home position
   */
  @Override
  public void execute() {
//    TODO: implement home
//    model.moveTo(0, 0);
  }
}
