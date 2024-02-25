package model.command.queries;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import slogo.model.api.LineModel;
import slogo.model.api.TurtleModel;
import slogo.model.command.Command;
import slogo.model.command.queries.TurtleQueryCommand;
import slogo.model.command.queries.TurtleQueryCommand.QueryType;

public class QueriesTest {

  @Test
  public void testXCOR() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command xcor = new TurtleQueryCommand(turtleModel, QueryType.XCOR);
    double result = xcor.execute();
    assertEquals(0, result);
  }

  @Test
  public void testYCOR() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command ycor = new TurtleQueryCommand(turtleModel, QueryType.YCOR);
    double result = ycor.execute();
    assertEquals(0, result);
  }

  @Test
  public void testHEADING() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command heading = new TurtleQueryCommand(turtleModel, QueryType.HEADING);
    double result = heading.execute();
    assertEquals(0, result);
  }

  @Test
  public void testPENDOWN() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command pendown = new TurtleQueryCommand(turtleModel, QueryType.PENDOWN);
    double result = pendown.execute();
    assertEquals(0, result);
  }

  @Test
  public void testSHOWING() {
    LineModel lineModel = new LineModel();
    TurtleModel turtleModel = new TurtleModel(lineModel);
    Command showing = new TurtleQueryCommand(turtleModel, QueryType.SHOWING);
    double result = showing.execute();
    assertEquals(1, result);
  }

}
