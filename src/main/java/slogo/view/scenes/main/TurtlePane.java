package slogo.view.scenes.main;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import slogo.model.api.TurtleModel;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * TurtlePane representing where Turtle is rendered
 */
public class TurtlePane implements Observer {

  private final Pane displayPane;
  private final Rectangle turtleGraphic;

  /**
   * TurtlePane Constructor. Initializes display pane and turtle graphic
   *
   * @param width  width of display
   * @param height height of display
   */
  public TurtlePane(int width, int height) {
    // initialize pane
    displayPane = new Pane();
    displayPane.setPrefSize(width, height * 0.75);
    displayPane.setStyle("-fx-background-color: #e0e0e0;");

    // initialize Turtle graphic
    turtleGraphic = new Rectangle(20, 20, Color.GREEN);
    turtleGraphic.setX(width / 2.0 - 10); // Center X
    turtleGraphic.setY(height * 0.75 / 2.0 - 10); // Center Y
    displayPane.getChildren().add(turtleGraphic);
  }

  /**
   * Update function for the turtle view
   *
   * @param observable that triggered update notification
   */
  @Override
  public void update(Observable observable) {
    if (observable instanceof TurtleModel turtleModel) {
      // get new location of turtle
      double centerX = turtleGraphic.getParent().getBoundsInLocal().getWidth() / 2.0;
      double centerY = turtleGraphic.getParent().getBoundsInLocal().getHeight() / 2.0;

      // update location of new turtle state
      turtleGraphic.setX(centerX + turtleModel.getX() - turtleGraphic.getWidth() / 2.0);
      turtleGraphic.setY(centerY - turtleModel.getY() - turtleGraphic.getHeight() / 2.0);
      turtleGraphic.setRotate(-turtleModel.getOrientation());
    }
  }

  public Pane getDisplayPane() {
    return displayPane;
  }
}