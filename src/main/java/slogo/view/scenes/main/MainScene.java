package slogo.view.scenes.main;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import slogo.controller.CommandController;
import slogo.view.buttons.ControlButtonsBox;
import slogo.view.scenes.Scene;
import slogo.view.tabs.SideTabPane;

/**
 * MainScene represents the Main SLogo Scene.
 */
public class MainScene implements Scene {

  private final TurtlePane turtlePane;
  private final InputPane inputPane;
  private final SideTabPane sideTabPane;
  private final ControlButtonsBox controlButtonsBox;
  private javafx.scene.Scene scene;
  private BorderPane root;

  /**
   * Constructor for Display.
   *
   * @param width             width
   * @param height            height
   * @param commandController commandController
   */
  public MainScene(int width, int height, CommandController commandController) {
    // initialize panes
    this.turtlePane = new TurtlePane(width, height);
    this.inputPane = new InputPane(height, commandController);
    this.sideTabPane = new SideTabPane();
    this.controlButtonsBox = new ControlButtonsBox();

    // subscribe panes to models
    commandController.observeTurtle(turtlePane);
    commandController.observeLines(turtlePane);
    commandController.observeHistory(sideTabPane);

    // initialize scene
    initializeScene(width, height);
  }

  /**
   * Initialize the scene.
   *
   * @param width  width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    root = new BorderPane();
    root.setId("Main Scene");

    BorderPane topPane = new BorderPane();
    topPane.setTop(turtlePane.getDisplayPane());

    // Add ControlButtons to the right of the top pane
    topPane.setBottom(controlButtonsBox);

    root.setTop(topPane);
    BorderPane bottomPane = new BorderPane();
    bottomPane.setLeft(inputPane.getInputBox());
    bottomPane.setRight(sideTabPane);
    root.setBottom(bottomPane);
    this.scene = new javafx.scene.Scene(root, width, height);
  }

  /**
   * Get the scene.
   *
   * @return scene
   */
  @Override
  public javafx.scene.Scene getScene() {
    return this.scene;
  }

  @Override
  public Node getRoot() {
    return root;
  }
}
