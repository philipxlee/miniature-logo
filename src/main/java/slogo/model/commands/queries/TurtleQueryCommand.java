package slogo.model.commands.queries;

import slogo.model.api.TurtleModel;
import slogo.model.commands.Command;

public class TurtleQueryCommand implements Command {

  private final TurtleModel model;
  private final QueryType queryType;
  private double result;

  /**
   * Constructor for the TurtleQueryCommand, which queries the turtle for its current state
   *
   * @param model     the model to query
   * @param queryType the type of query to perform
   */
  public TurtleQueryCommand(TurtleModel model, QueryType queryType) {
    this.model = model;
    this.queryType = queryType;
  }

  /**
   * Executes the respective query command for the turtle
   */
  @Override
  public void execute() {
    switch (queryType) {
//      case XCOR -> result = model.getXCor();
//      case YCOR -> result = model.getYCor();
//      case HEADING -> result = model.getHeading();
//      case PENDOWN -> result = model.isPenDown() ? 1 : 0;
//      case SHOWING -> result = model.isShowing() ? 1 : 0;
      case PLACEHOLDER -> result = 999999999;
      default -> throw new IllegalStateException("Unexpected query: " + queryType);
    }
  }

  /**
   * Returns the result of the query
   *
   * @return the result of the query
   */
  public double getResult() {
    return result;
  }

  private enum QueryType {
    XCOR, YCOR, HEADING, PENDOWN, SHOWING, PLACEHOLDER
  }
}
