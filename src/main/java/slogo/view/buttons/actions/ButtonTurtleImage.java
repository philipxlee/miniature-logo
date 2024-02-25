package slogo.view.buttons.actions;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import slogo.view.buttons.ButtonAction;

import java.io.File;

  public class ButtonTurtleImage implements ButtonAction {

    @Override
    public void performAction() {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Select an Image File");
      fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
      );

      // Get the selected file
      File selectedFile = fileChooser.showOpenDialog(null); // You can pass a Stage here if needed

      // If the file is not null, load the image and set it as the turtle's image
      if (selectedFile != null) {
        Image image = new Image(selectedFile.toURI().toString());
        setTurtleImage(image); // Implement this method to set the turtle image
      }
    }

    // Method to set the turtle image
    private void setTurtleImage(Image image) {
      // Implement how to set the turtle image
      // For example, if you have a method in your UI class to set the turtle image:
      // ui.setTurtleImage(image);
    }
  }

