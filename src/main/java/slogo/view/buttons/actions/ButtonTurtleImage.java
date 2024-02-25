package slogo.view.buttons.actions;


import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import slogo.view.buttons.ButtonAction;
import slogo.view.scenes.main.TurtlePane;

/**
 * ButtonTurtleImage is the button to load a new image for the turtle.
 */
public class ButtonTurtleImage implements ButtonAction {

  /**
   * Click handler for load image button.
   */
  @Override
  public void performAction() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select an Image File");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
    );

    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
      Image image = new Image(selectedFile.toURI().toString());
      TurtlePane.setTurtleImage(image);
    }
  }
}

