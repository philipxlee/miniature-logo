package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class ClearScreenCommand implements Command {

  private final TurtleModel model;

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
