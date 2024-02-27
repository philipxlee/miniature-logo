package slogo.view.buttons;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import slogo.view.buttons.actions.ButtonTurtleImage;
import slogo.view.buttons.actions.PenColorButton;

/**
 * A custom HBox for displaying control buttons with associated actions.
 */
public class ControlButtonsBox extends HBox {

  private final List<ButtonAction> buttonActions = new ArrayList<>();

  /**
   * Constructs a new ControlButtonsBox.
   */
  public ControlButtonsBox() {
    super();
    setupLayout();
  }

  private void setupLayout() {
    this.setSpacing(10);
    this.setPadding(new Insets(10));
    addButton("Load Turtle Image", new ButtonTurtleImage());

    // Button to change the pen color (addButton not used to allow for ColorPicker functionality)
    PenColorButton penColorButton = new PenColorButton();
    this.getChildren().add(penColorButton);
  }

  /**
   * Adds a new button with the specified text and action.
   *
   * @param buttonText The text to display on the button.
   * @param action     The action to perform when the button is clicked.
   */
  public void addButton(String buttonText, ButtonAction action) {
    Button button = new Button(buttonText);
    button.setOnAction(event -> action.performAction());
    buttonActions.add(action);
    this.getChildren().add(button);
  }

  /**
   * Clears all buttons from the ControlButtonsBox.
   */
  public void clearButtons() {
    this.getChildren().clear();
    buttonActions.clear();
  }

}
