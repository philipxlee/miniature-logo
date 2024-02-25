package slogo.view.scenes.main;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import slogo.model.api.TurtleModel;
import slogo.observer.Observable;
import slogo.observer.Observer;
import slogo.model.api.TurtleLineModel;

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
    displayPane.setPrefSize(width, height * 0.5);
    displayPane.setStyle("-fx-background-color: #e0e0e0;");

    // initialize Turtle graphic
    turtleGraphic = new Rectangle(20, 20, Color.GREEN);
    turtleGraphic.setX(width / 2.0 - 10); // Center X
    turtleGraphic.setY(height * 0.5 / 2.0 - 10); // Center Y
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

      double centerX = displayPane.getWidth() / 2.0;
      double centerY = displayPane.getHeight() / 2.0;

      // Update the turtle's graphic position to its center
      double turtleCenterX = centerX + turtleModel.getX() - turtleGraphic.getWidth() / 2.0;
      double turtleCenterY = centerY - turtleModel.getY() - turtleGraphic.getHeight() / 2.0;

      turtleGraphic.setX(turtleCenterX);
      turtleGraphic.setY(turtleCenterY);
      turtleGraphic.setRotate(-turtleModel.getOrientation());

      // set visibility of turtle graphic
      turtleGraphic.setVisible(turtleModel.getTurtleVisibility());

      // draw lines
      drawLines(turtleModel);
    }
  }

  public Pane getDisplayPane() {
    return displayPane;
  }

  private void drawLines(TurtleModel model) {
    double centerX = displayPane.getWidth() / 2.0;
    double centerY = displayPane.getHeight() / 2.0;

    for (TurtleLineModel line : model.getLines()) {
      Line fxLine = new Line();
      fxLine.setStartX(centerX + line.getStartX());
      fxLine.setStartY(centerY - line.getStartY());
      fxLine.setEndX(centerX + line.getEndX());
      fxLine.setEndY(centerY - line.getEndY());
      fxLine.setStroke(Color.BLACK);
      fxLine.setStrokeWidth(3);  // Consider a thinner line for better accuracy

      displayPane.getChildren().add(fxLine);
    }
    turtleGraphic.toFront();  // Ensure the turtle graphic is always on top
  }
}