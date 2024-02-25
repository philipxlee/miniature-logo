package slogo.model.api.command.queries;

import slogo.model.api.data.TurtleModel;
import slogo.model.api.command.Command;

/**
 * TurtleQueryCommand queries the turtle model for a property.
 */
public class TurtleQueryCommand implements Command {

  private final TurtleModel model;
  private final QueryType queryType;

  /**
   * Constructor for the TurtleQueryCommand, which queries the turtle for its current state.
   *
   * @param model     the model to query
   * @param queryType the type of query to perform
   */
  public TurtleQueryCommand(TurtleModel model, QueryType queryType) {
    this.model = model;
    this.queryType = queryType;
  }

  /**
   * Executes the respective query command for the turtle.
   *
   * @return the result of the query
   */
  @Override
  public Double execute() {
    double result = 0;
    switch (queryType) {
      case XCOR -> result = model.getPositionX();
      case YCOR -> result = model.getPositionY();
      case HEADING -> result = model.getOrientation();
      case PENDOWN -> result = model.getPenDown() ? 1 : 0;
      case SHOWING -> result = model.getVisible() ? 1 : 0;
      default -> throw new IllegalStateException("Unexpected query: " + queryType);
    }
    return result;
  }

  /**
   * Defines types for each type of query.
   */
  public enum QueryType {
    XCOR, YCOR, HEADING, PENDOWN, SHOWING
  }
}
