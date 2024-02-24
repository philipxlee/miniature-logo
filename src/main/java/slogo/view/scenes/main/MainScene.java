package slogo.view.scenes.main;

import javafx.scene.layout.BorderPane;
import slogo.controller.CommandController;
import slogo.view.buttons.ControlButtonsBox;
import slogo.view.scenes.Scene;
import slogo.view.tabs.SideTabPane;

public class MainScene implements Scene {

  private final TurtlePane turtlePane;
  private final InputPane inputPane;
  private final SideTabPane sideTabPane;
  private final ControlButtonsBox controlButtonsBox;

  private javafx.scene.Scene scene;

  /**
   * Constructor for Display
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   */
  public MainScene(int width, int height, CommandController commandController) {
    this.turtlePane = new TurtlePane(width, height);
    this.inputPane = new InputPane(width, height, commandController);
    this.sideTabPane = new SideTabPane();
    this.controlButtonsBox = new ControlButtonsBox(); // Instantiate ControlButtons

    commandController.observeTurtle(turtlePane);
    initializeScene(width, height);
  }

  /**
   * Initialize the scene
   *
   * @param width  width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    BorderPane root = new BorderPane();

    BorderPane topPane = new BorderPane();
    topPane.setTop(turtlePane.getDisplayPane());

    // Add ControlButtons to the right of the top pane
    topPane.setBottom(controlButtonsBox);
//    BorderPane.setMargin(controlButtons, new Insets(10)); // Add margin to controlButtons

    root.setTop(topPane);
    BorderPane bottomPane = new BorderPane();
    bottomPane.setLeft(inputPane.getInputBox());
    bottomPane.setRight(sideTabPane);
    root.setBottom(bottomPane);
    this.scene = new javafx.scene.Scene(root, width, height);
  }

  /**
   * Get the scene
   *
   * @return scene
   */
  @Override
  public javafx.scene.Scene getScene() {
    return this.scene;
  }
}
