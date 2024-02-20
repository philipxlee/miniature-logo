package slogo.model.commands;

import slogo.model.api.TurtleModel;

public class HomeCommand implements Command {

  private final TurtleModel model;

  public HomeCommand(TurtleModel model) {
    this.model = model;
  }

  @Override
  public void execute() {
//    TODO: implement home
//    model.moveTo(0, 0);
  }
}
