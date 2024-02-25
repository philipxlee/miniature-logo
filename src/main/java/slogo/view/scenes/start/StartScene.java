package slogo.view.scenes.start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.controller.CommandController;
import slogo.controller.SceneSwitcher;
import slogo.view.scenes.Scene;
import slogo.view.scenes.main.MainScene;

/**
 * StartScene represents the Start SLogo Scene
 */
public class StartScene implements Scene {

  private static final int BUTTON_WIDTH = 135;
  private static final int BUTTON_HEIGHT = 40;
  private final SceneSwitcher switcher;
  private final CommandController commandController;
  private javafx.scene.Scene scene;

  /**
   * Constructor for StartScene.
   *
   * @param width             width
   * @param height            height
   * @param switcher          switcher
   * @param commandController commandController
   */
  public StartScene(int width, int height, SceneSwitcher switcher,
      CommandController commandController) {
    this.commandController = commandController;
    this.switcher = switcher;
    initializeScene(width, height);
  }


  /**
   * Initialize start screen with title and click handler
   *
   * @param width  width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    VBox parentBox = new VBox(10);
    parentBox.setAlignment(Pos.CENTER);

    // Initialize title text and add click handler
    Text title = new Text("SLogo");
    title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
    Text instruction = new Text("By: Prince Ahmed, Arnav Nayak, Philip Lee, Connor Johnson");
    instruction.setStyle("-fx-font-size: 16px;");

    Button startButton = createStartButton(width, height);
    Button loadButton = createLoadButton();
    Button languageButton = createLanguageButton();
    Button colorSchemeButton = createColorSchemeButton();

    parentBox.getChildren()
        .addAll(title, instruction, startButton, loadButton, languageButton, colorSchemeButton);
    this.scene = new javafx.scene.Scene(parentBox, width, height);
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

  /**
   * Create a "Start Game" button.
   *
   * @param width  width of the button
   * @param height height of the button
   * @return the created button
   */
  private Button createStartButton(int width, int height) {
    Button startButton = new Button("Start Game");
    startButton.setOnAction(
        event -> switcher.switchToScene(new MainScene(width, height, commandController)));
    startButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    return startButton;
  }

  /**
   * Create a "Load Session" button.
   *
   * @return the created button
   */
  private Button createLoadButton() {
    Button loadButton = new Button("Load Session");
    loadButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    // Add action for loading a session
    return loadButton;
  }

  /**
   * Create a "Select Language" button.
   *
   * @return the created button
   */
  private Button createLanguageButton() {
    Button languageButton = new Button("Select Language");
    languageButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    // Add action for selecting a language
    return languageButton;
  }

  /**
   * Create a "Select Color Scheme" button.
   *
   * @return the created button
   */
  private Button createColorSchemeButton() {
    Button colorSchemeButton = new Button("Select Color Scheme");
    colorSchemeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    // Add action for selecting a color scheme
    return colorSchemeButton;
  }
}
