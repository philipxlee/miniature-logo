package slogo.model.commands.turtle;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;

public class PenCommand implements Command {

  private final TurtleModel model;
  private final boolean penDown;

  /**
   * Constructs a PenCommand.
   *
   * @param model   the TurtleModel to affect
   * @param penDown true to put the pen down, false to lift it up
   */
  public PenCommand(TurtleModel model, boolean penDown) {
    this.model = model;
    this.penDown = penDown;
  }

  /**
   * Executes the pen command. Puts the pen down or lifts it up.
   */
  @Override
  public void execute() {
//    TODO: Create penDown() and penUp() in model
//    penDown ? model.penDown() : model.penUp();
  }
}