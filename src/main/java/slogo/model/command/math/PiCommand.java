package slogo.model.command.math;

import slogo.model.command.Command;

public class PiCommand implements Command {


  public PiCommand() {}

  @Override
  public void execute() { // should be double
    System.out.println(Math.PI); // placeholder
//    return Math.PI;
  }

}
