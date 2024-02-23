package slogo.view.scenes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class TurtleDisplay {
  private Pane displayPane;
  private Rectangle turtleGraphic;

  /**
   * Constructor for TurtleDisplay
   *
   * @param width   width of display
   * @param height  height of display
   */
  public TurtleDisplay(int width, int height) {
    initializeDisplay(width, height);
  }

  /**
   * Returns the display pane
   *
   * @return Pane
   */
  public Pane getDisplayPane() {
    return displayPane;
  }

  /**
   * Returns the turtle graphic
   *
   * @return
   */
  public Rectangle getTurtleGraphic() {
    return turtleGraphic;
  }

  private void initializeDisplay(int width, int height) {
    displayPane = new Pane();
    displayPane.setPrefSize(width, height * 0.75);
    displayPane.setStyle("-fx-background-color: #e0e0e0;");

    // Make turtle graphic
    turtleGraphic = new Rectangle(20, 20, Color.GREEN);
    turtleGraphic.setX(width / 2.0 - 10); // Center X
    turtleGraphic.setY(height * 0.75 / 2.0 - 10); // Center Y
    displayPane.getChildren().add(turtleGraphic);
  }

}
