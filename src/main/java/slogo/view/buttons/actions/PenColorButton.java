package slogo.view.buttons.actions;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.view.buttons.ButtonAction;

public class PenColorButton extends ColorPicker implements ButtonAction {

  /**
   * Constructs a new PenColorButton.
   */
  public PenColorButton() {
    super(Color.BLACK); // Initialize with default color, e.g., black
    setOnAction(event -> performAction());
  }

  /**
   * Performs the action associated with the button.
   */
  @Override
  public void performAction() {
    Color selectedColor = getValue(); // This gets the newly selected color.
    System.out.println("Selected color: " + selectedColor);

  }

}