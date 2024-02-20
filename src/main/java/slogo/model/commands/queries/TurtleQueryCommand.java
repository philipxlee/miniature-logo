package slogo.model.commands.queries;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;

public class TurtleQueryCommand implements Command {

  public enum QueryType {
    XCOR, YCOR, HEADING, PENDOWN, SHOWING
  }

  private final TurtleModel model;
  private final QueryType queryType;
  private double result;

  public TurtleQueryCommand(TurtleModel model, QueryType queryType) {
    this.model = model;
    this.queryType = queryType;
  }

  @Override
  public void execute() {
    switch (queryType) {
      case XCOR:
        result = model.getXCor();
        break;
      case YCOR:
        result = model.getYCor();
        break;
      case HEADING:
        result = model.getHeading();
        break;
      case PENDOWN:
        result = model.isPenDown() ? 1 : 0;
        break;
      case SHOWING:
        result = model.isShowing() ? 1 : 0;
        break;
    }
  }

  public double getResult() {
    return result;
  }
}
