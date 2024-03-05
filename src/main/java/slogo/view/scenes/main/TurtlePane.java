package slogo.view.scenes.main;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.model.api.data.LineModel;
import slogo.model.api.data.TurtleModel;
import slogo.observer.BackgroundObservable;
import slogo.observer.Observable;
import slogo.observer.Observer;
import slogo.observer.PenColorObservable;

/**
 * TurtlePane representing where Turtle is rendered.
 */
public class TurtlePane implements Observer {

  public static final double RATIO_TURTLE_DISPLAY = 0.5;
  public static final String DEFAULT_TURTLE_IMAGE_PATH = "/default_turtle.png";
  private static ImageView turtleImageView;
  private final Pane displayPane;
  private final Set<Line> linesDrawn = new HashSet<>();
  private Color currentPenColor = Color.BLACK;

  /**
   * TurtlePane Constructor. Initializes display pane and turtle graphic
   *
   * @param width  width of display
   * @param height height of display
   */
  public TurtlePane(int width, int height) {
    // initialize pane
    displayPane = new Pane();
    displayPane.setPrefSize(width, height * RATIO_TURTLE_DISPLAY);
    displayPane.getStyleClass().add("display-pane-background");

    // initialize Turtle graphic
    turtleImageView = new ImageView();
    turtleImageView.setImage(
        new Image(
            Objects.requireNonNull(
                TurtlePane.class.getResourceAsStream(DEFAULT_TURTLE_IMAGE_PATH))));
    turtleImageView.setFitWidth(20);
    turtleImageView.setFitHeight(20);
    turtleImageView.setX(width / 2.0 - 10); // Center X
    turtleImageView.setY(height * RATIO_TURTLE_DISPLAY / 2.0 - 10); // Center Y
    displayPane.getChildren().add(turtleImageView);
  }

  /**
   * Set the turtle's image to the specified image.
   *
   * @param image The image to set for the turtle.
   */
  public static void setTurtleImage(Image image) {
    turtleImageView.setImage(image);
  }

  /**
   * Update function for the turtle view.
   *
   * @param observable that triggered update notification
   */
  @Override
  public void update(Observable observable) {

    if (observable instanceof BackgroundObservable colorObservable) {
      displayPane.setStyle("-fx-background-color: " + colorObservable.getColor() + ";");
    }

    if (observable instanceof PenColorObservable penColorObservable) {
      currentPenColor = Color.web(penColorObservable.getColor());
    }

    if (observable instanceof TurtleModel turtleModel) {
      drawTurtle(turtleModel);
    }
    if (observable instanceof LineModel lineModel) {
      if (lineModel.getAvailableLines() == 0) {
        displayPane.getChildren().removeIf(node -> node instanceof Line);
        linesDrawn.clear();
      }
      drawLines(lineModel);
    }
  }

  /**
   * Set the background color of the display pane to the color observable.
   *
   * @param colorObservable The observable to set the background color to.
   */
  public void setBackgroundColorObservable(BackgroundObservable colorObservable) {
    colorObservable.addObserver(this);
  }

  /**
   * Set the pen color observable to the specified observable.
   *
   * @param penColorObservable The observable to set the pen color to.
   */
  public void setPenColorObservable(PenColorObservable penColorObservable) {
    penColorObservable.addObserver(this);
  }

  /**
   * Return pane representing TurtleView.
   *
   * @return Pane object of view
   */
  public Pane getDisplayPane() {
    return displayPane;
  }


  private void drawTurtle(TurtleModel turtleModel) {
    double centerX = displayPane.getWidth() / 2.0;
    double centerY = displayPane.getHeight() / 2.0;

    // Update the turtle's graphic position to its center
    double offsetX = (turtleModel.getPositionX() - turtleImageView.getFitWidth() / 2.0);
    double offsetY = (turtleModel.getPositionY() + turtleImageView.getFitHeight() / 2.0);
    double turtleCenterX = centerX + offsetX;
    double turtleCenterY = centerY - offsetY;

    turtleImageView.setX(turtleCenterX);
    turtleImageView.setY(turtleCenterY);
    turtleImageView.setRotate(-turtleModel.getOrientation());

    // set visibility of turtle graphic
    turtleImageView.setVisible(turtleModel.getVisible());
  }

  private void drawLines(LineModel lineModel) {
    // display new lines
    double centerX = displayPane.getWidth() / 2.0;
    double centerY = displayPane.getHeight() / 2.0;
    int lines = lineModel.getAvailableLines();

    while (lines > 0) {
      slogo.model.line.Line line = lineModel.getLine();
      Line fxLine = new Line();
      fxLine.setStartX(centerX + line.startX());
      fxLine.setStartY(centerY - line.startY());
      fxLine.setEndX(centerX + line.endX());
      fxLine.setEndY(centerY - line.endY());
      fxLine.setStroke(currentPenColor);
      fxLine.setStrokeWidth(3);  // Consider a thinner line for better accuracy
      displayPane.getChildren().add(fxLine);
      linesDrawn.add(fxLine);
      lines -= 1;
    }
    turtleImageView.toFront();  // Ensure the turtle graphic is always on top
  }

}