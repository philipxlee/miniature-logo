package slogo.view.buttons;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import slogo.view.scenes.main.InputPane;
import slogo.view.scenes.main.TurtlePane;
import slogo.view.tabs.SideTabPane;

// A superclass for all the control buttons
public abstract class ControlButton extends Button {

  // A constructor that takes a text and a style for the button
  public ControlButton(String text, String style) {
    super(text); // Call the superclass constructor
    this.setStyle(style); // Set the style of the button
  }

  // An abstract method that defines the action for the button
  public abstract void handleAction(Stage primaryStage, TurtlePane turtlePane, InputPane inputPane, SideTabPane sideTabPane);
}
