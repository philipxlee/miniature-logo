package slogo.view.scenes.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
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
  public static final Double TRAVERSAL_RATE = 100.0;
  public static final String DEFAULT_TURTLE_IMAGE_PATH = "/default_turtle.png";
  private static ImageView turtleImageView;
  private final Pane displayPane;
  private Color currentPenColor = Color.BLACK;
  private final List<Line> linesDrawn = new ArrayList<>();
  private ParallelTransition currentAnimation;
  private double animationSpeed;
  private boolean penDown = true;
  private double penThickness = 1.0;

  /**
   * TurtlePane Constructor. Initializes display pane and turtle graphic
   *
   * @param width  width of display
   * @param height height of display
   */
  public TurtlePane(int width, int height) {
    displayPane = new Pane();
    displayPane.setPrefSize(width, height * RATIO_TURTLE_DISPLAY);
    displayPane.getStyleClass().add("display-pane-background");
    animationSpeed = 1;
    initializeTurtle(width, height);
  }

  /**
   * Set the turtle's image to the specified image.
   *
   * @param image The image to set for the turtle.
   */
  public void setTurtleImage(Image image) {
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
      List<Animation> animations = drawTurtle(turtleModel);
      currentAnimation = new ParallelTransition(turtleImageView);
      currentAnimation.getChildren().addAll(animations);
      if (turtleModel.getPenDown()) {
        currentAnimation.getChildren().add(drawLines(turtleModel));
      }
      currentAnimation.play();
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

  private List<Animation> drawTurtle(TurtleModel turtleModel) {
    List<Animation> animations = new ArrayList<>();

    // check if turtle has moved
    boolean hasMoved = turtleModel.getPositionX() != turtleModel.getPrevX() ||
        turtleModel.getPositionY() != turtleModel.getPrevY();

    // check if turtle has rotated
    boolean hasRotated = turtleModel.getOrientation() != turtleModel.getPrevOrientation();

    // if the turtle has moved, create and play a movement animation
    if (hasMoved) {
      animations.add(createMovementAnimation(turtleModel));
    }

    // if the turtle has rotated, create and play a rotation animation
    if (hasRotated) {
      animations.add(createRotationAnimation(turtleModel));
    }

    // set visibility of turtle graphic
    turtleImageView.setVisible(turtleModel.getVisible());

    return animations;
  }

  private ParallelTransition drawLines(TurtleModel turtleModel) {
    if (!penDown) {
      return new ParallelTransition();  // Return an empty transition if pen is up
    }

    ParallelTransition parallelTransition = new ParallelTransition();
    linesDrawn.clear();

    double centerX = displayPane.getWidth() / 2.0;
    double centerY = displayPane.getHeight() / 2.0;
    double startX = centerX + turtleModel.getPrevX();
    double startY = centerY - turtleModel.getPrevY();
    double endX = centerX + turtleModel.getPositionX();
    double endY = centerY - turtleModel.getPositionY();

    double distance = Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2));
    double duration = distance / (TRAVERSAL_RATE + animationSpeed);
    double segmentDuration = 0.001;
    int numSegments = (int) (duration / segmentDuration);

    double deltaX = (endX - startX) / numSegments;
    double deltaY = (endY - startY) / numSegments;

    for (int i = 0; i < numSegments; i++) {
      Line pathSegment = new Line(startX + (i * deltaX), startY + (i * deltaY),
          startX + ((i + 1) * deltaX), startY + ((i + 1) * deltaY));
      pathSegment.setStroke(currentPenColor);
      pathSegment.setStrokeWidth(penThickness);

      double pauseDuration = (i + 1) * duration / numSegments;
      PauseTransition pause = new PauseTransition(Duration.seconds(pauseDuration));
      pause.setOnFinished(e -> {
        displayPane.getChildren().add(pathSegment);
        linesDrawn.add(pathSegment);
      });
      parallelTransition.getChildren().add(pause);
    }

    return parallelTransition;
  }

  private void initializeTurtle(int width, int height) {
    turtleImageView = new ImageView();
    turtleImageView.setImage(new Image(
        Objects.requireNonNull(TurtlePane.class.getResourceAsStream(DEFAULT_TURTLE_IMAGE_PATH))));
    turtleImageView.setFitWidth(20);
    turtleImageView.setFitHeight(20);

    // Center the turtle in the pane
    resetTurtlePosition(width, height);
    displayPane.getChildren().add(turtleImageView);
  }

  private void resetTurtlePosition(int width, int height) {
    // Center the turtle in the pane
    turtleImageView.setX(width / 2.0 - 10); // Center X
    turtleImageView.setY(height * RATIO_TURTLE_DISPLAY / 2.0 - 10); // Center Y
  }

  private PathTransition createMovementAnimation(TurtleModel turtleModel) {
    double centerX = displayPane.getWidth() / 2.0;
    double centerY = displayPane.getHeight() / 2.0;
    double distance = Math.sqrt(
        Math.pow((turtleModel.getPositionX() - turtleModel.getPrevX()), 2) + Math.pow(
            (turtleModel.getPositionY() - turtleModel.getPrevY()), 2));
    double duration = distance / (TRAVERSAL_RATE + animationSpeed);

    Path path = new Path();
    path.getElements().add(new MoveTo(centerX + turtleModel.getPrevX(),
        centerY - turtleModel.getPrevY())); // Starting position
    path.getElements().add(new LineTo(centerX + turtleModel.getPositionX(),
        centerY - turtleModel.getPositionY())); // Ending position

    return new PathTransition(Duration.seconds(duration), path, turtleImageView);
  }

  private RotateTransition createRotationAnimation(TurtleModel turtleModel) {
    RotateTransition rotateTransition = new RotateTransition();
    rotateTransition.setDuration(Duration.seconds(1));
    rotateTransition.setByAngle(-turtleModel.getOrientation() + turtleModel.getPrevOrientation());
    rotateTransition.setNode(turtleImageView);

    return rotateTransition;
  }

  public void pauseAnimation() {
    if (currentAnimation != null) {
      currentAnimation.pause();
    }
  }

  public void playAnimation() {
    if (currentAnimation != null && currentAnimation.getStatus() == Animation.Status.PAUSED) {
      currentAnimation.play();
    }
  }

  public void replayAnimation() {
    if (currentAnimation != null) {
      linesDrawn.forEach(displayPane.getChildren()::remove);
      linesDrawn.clear();
      currentAnimation.stop();
      currentAnimation.playFromStart();
    }
  }

  public void adjustSpeed(double adjust) {
    if(adjust == 0.0) {
      animationSpeed = -50.0;
      return;
    }
    animationSpeed = Math.max(TRAVERSAL_RATE + adjust, 1.0);
  }

  // Methods to update pen properties
  public void setPenUp(boolean isUp) {
    this.penDown = !isUp;
  }

  public void setPenColor(Color color) {
    this.currentPenColor = color;
  }

  public void setPenThickness(double thickness) {
    this.penThickness = thickness;
  }
}