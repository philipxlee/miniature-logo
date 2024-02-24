package slogo.view.buttons.actions;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import slogo.view.buttons.ButtonAction;

import java.io.File;
import slogo.view.scenes.main.TurtlePane;

public class ButtonTurtleImage implements ButtonAction {

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

