package slogo.view;

import slogo.model.TurtleModel;
import slogo.utils.Observable;
import slogo.utils.Observer;

/**
 * TurtleView representing view where Turtle is rendered
 *
 * @author Arnav Nayak
 */
public class TurtleView implements Observer {

  /**
   * Re-render the Turtle when state is changed
   *
   * @param observable: observable triggering the notification
   */
  @Override
  public void update(Observable observable) {
    if (observable instanceof TurtleModel turtleModel) {
      System.out.println("View: TurtleModel updated with new position: " + turtleModel.getX() + ", "
          + turtleModel.getY());
    }
  }
}
