package slogo.view.buttons;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import slogo.view.scenes.main.InputPane;
import slogo.view.scenes.main.TurtlePane;
import slogo.view.tabs.SideTabPane;

/**
 * ControlButton is a superclass for all buttons.
 */
public abstract class ControlButton extends Button {

  /**
   * ControlButton constructor.
   *
   * @param text  text of button
   * @param style style of button
   */
  public ControlButton(String text, String style) {
    super(text);
    this.setStyle(style);
  }

  /**
   * An abstract method that defines the action for the button given all panes.
   *
   * @param primaryStage is the primary stage
   * @param turtlePane   is the turtle pane
   * @param inputPane    is the input pane
   * @param sideTabPane  is the side tab pane
   */
  public abstract void handleAction(Stage primaryStage, TurtlePane turtlePane, InputPane inputPane,
      SideTabPane sideTabPane);
}
