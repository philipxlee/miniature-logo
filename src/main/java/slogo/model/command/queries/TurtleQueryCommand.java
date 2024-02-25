package slogo.model.command.queries;

import slogo.model.api.TurtleModel;
import slogo.model.command.Command;

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
      case XCOR -> result = model.getX();
      case YCOR -> result = model.getY();
      case HEADING -> result = model.getOrientation();
      case PENDOWN -> result = model.getPenDown() ? 1 : 0;
      case SHOWING -> result = model.getVisible() ? 1 : 0;
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
    XCOR, YCOR, HEADING, PENDOWN, SHOWING
  }
}
