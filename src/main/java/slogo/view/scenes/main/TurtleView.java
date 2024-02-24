package slogo.view.scenes.main;

import javafx.scene.shape.Rectangle;
import slogo.model.api.TurtleModel;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * TurtleView representing view where Turtle is rendered
 */
public class TurtleView implements Observer {

  private Rectangle turtleGraphic;

  /**
   * Constructor for TurtleView
   *
   * @param turtleGraphic the rectangle representing the turtle
   */
  public TurtleView(Rectangle turtleGraphic) {
    this.turtleGraphic = turtleGraphic;
  }

  /**
   * Re-render the Turtle when state is changed
   *
   * @param observable observable triggering the notification
   */
  @Override
  public void update(Observable observable) {
    if (observable instanceof TurtleModel turtleModel) {
      double centerX = turtleGraphic.getParent().getBoundsInLocal().getWidth() / 2.0;
      double centerY = turtleGraphic.getParent().getBoundsInLocal().getHeight() / 2.0;

      turtleGraphic.setX(centerX + turtleModel.getX() - turtleGraphic.getWidth() / 2.0);
      turtleGraphic.setY(centerY - turtleModel.getY() - turtleGraphic.getHeight() / 2.0);

      // Update the turtle's orientation.
      // NOTE: The rotation in JavaFX is counter-clockwise, so no need to invert the angle.
      // Double check this though
      turtleGraphic.setRotate(-turtleModel.getOrientation());
    }
  }
}
