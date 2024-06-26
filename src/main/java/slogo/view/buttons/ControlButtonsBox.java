package slogo.view.buttons;

import java.io.File;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import slogo.observer.BackgroundObservable;
import slogo.observer.PenColorObservable;
import slogo.view.buttons.actions.PenColorButton;
import slogo.view.buttons.actions.TurtleBackgroundButton;
import slogo.view.scenes.main.TurtlePane;

/**
 * A custom HBox for displaying control buttons with associated actions.
 */
public class ControlButtonsBox extends HBox {

  private final String pauseString = "Pause";
  private final String playString = "Play";
  private final BackgroundObservable colorObservable;
  private final PenColorObservable penColorObservable;
  private final TurtlePane turtlePane;

  /**
   * Constructs a new ControlButtonsBox.
   */
  public ControlButtonsBox(BackgroundObservable colorObservable, PenColorObservable penObservable,
      TurtlePane turtlePane) {
    super();
    this.colorObservable = colorObservable;
    this.penColorObservable = penObservable;
    this.turtlePane = turtlePane;
    setupLayout();
  }

  /**
   * Adds a new button with the specified text and action.
   *
   * @param buttonText The text to display on the button.
   * @param action     The action to perform when the button is clicked.
   */
  public void addButton(String buttonText, Consumer<Button> action) {
    Button button = new Button(buttonText);
    button.setOnAction(event -> action.accept(button));
    button.getStyleClass().add("load-button");
    this.getChildren().add(button);
  }

  private void setupLayout() {
    this.setSpacing(10);
    this.setPadding(new Insets(10));

    addButton("Load Turtle Image", button -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Select an Image File");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
      );

      File selectedFile = fileChooser.showOpenDialog(null);

      if (selectedFile != null) {
        Image image = new Image(selectedFile.toURI().toString());
        turtlePane.setTurtleImage(image);
      }
    });

    addButton(pauseString, button -> {
      if (pauseString.equals(button.getText())) {
        turtlePane.pauseAnimation();
        button.setText(playString);
      } else {
        turtlePane.playAnimation();
        button.setText(pauseString);
      }
    });

    addButton("Replay", button -> {
      turtlePane.replayAnimation();
    });

    Slider slider = new Slider(0, 1, 0.5);
    slider.valueProperty().addListener(
        (observable, oldValue, newValue) -> turtlePane.adjustSpeed(newValue.doubleValue() * 100));
    this.getChildren().add(slider);

    // Button to change the pen color (addButton not used to allow for ColorPicker functionality)
    PenColorButton penColorButton = new PenColorButton(penColorObservable);
    this.getChildren().add(penColorButton);

    TurtleBackgroundButton turtleBackgroundButton = new TurtleBackgroundButton(colorObservable);
    this.getChildren().add(turtleBackgroundButton);
  }
}
