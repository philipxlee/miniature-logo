package slogo.view.buttons;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import slogo.controller.config.LanguageController;
import slogo.observer.BackgroundObservable;
import slogo.observer.PenColorObservable;
import slogo.view.buttons.actions.ButtonTurtleImage;
import slogo.view.buttons.actions.PenColorButton;
import slogo.view.buttons.actions.TurtleBackgroundButton;

/**
 * A custom HBox for displaying control buttons with associated actions.
 */
public class ControlButtonsBox extends HBox {

  private final List<ButtonAction> buttonActions = new ArrayList<>();
  private final BackgroundObservable colorObservable;
  private final PenColorObservable penColorObservable;

  /**
   * Constructs a new ControlButtonsBox.
   */
  public ControlButtonsBox(BackgroundObservable colorObservable, PenColorObservable penObservable) {
    super();
    this.colorObservable = colorObservable;
    this.penColorObservable = penObservable;
    setupLayout();
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
    button.getStyleClass().add("load-button");
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

  private void setupLayout() {
    this.setSpacing(10);
    this.setPadding(new Insets(10));
    addButton(LanguageController.getText("LoadTurtleImage"), new ButtonTurtleImage());
    // Button to change the pen color (addButton not used to allow for ColorPicker functionality)
    PenColorButton penColorButton = new PenColorButton(penColorObservable);
    this.getChildren().add(penColorButton);

    TurtleBackgroundButton turtleBackgroundButton = new TurtleBackgroundButton(colorObservable);
    this.getChildren().add(turtleBackgroundButton);

  }


}
