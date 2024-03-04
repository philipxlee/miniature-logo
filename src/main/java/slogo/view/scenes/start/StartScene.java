package slogo.view.scenes.start;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogo.controller.CommandController;
import slogo.controller.LoadButtonController;
import slogo.controller.SceneSwitcher;
import slogo.controller.ThemeController;
import slogo.view.scenes.Scene;
import slogo.view.scenes.main.MainScene;

/**
 * StartScene represents the Start SLogo Scene.
 */
public class StartScene implements Scene {

  private static final String BUTTON_STYLE = "button-color";
  private static final int BUTTON_WIDTH = 135;
  private static final int BUTTON_HEIGHT = 40;
  private final SceneSwitcher switcher;
  private final CommandController commandController;
  private javafx.scene.Scene scene;
  private VBox parentBox;

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
   * Initialize start screen with title and click handler.
   *
   * @param width  width
   * @param height height
   */
  @Override
  public void initializeScene(int width, int height) {
    parentBox = new VBox(10);
    parentBox.setId("Start Scene");
    parentBox.setAlignment(Pos.CENTER);

    // Initialize title text and add click handler
    Text title = new Text("SLogo");
    title.getStyleClass().add("title-text");
    Text instruction = new Text("By: Prince Ahmed, Arnav Nayak, Philip Lee, Connor Johnson");
    instruction.getStyleClass().add("instruction-text");

    Button startButton = createStartButton(width, height);
    Button loadButton = createLoadButton(width, height);
    Button languageButton = createLanguageButton();
    ComboBox<String> colorSchemeButton = createColorSchemeButton();

    parentBox.getChildren()
        .addAll(title, instruction, startButton, loadButton, languageButton, colorSchemeButton);
    this.scene = new javafx.scene.Scene(parentBox, width, height);
    ThemeController.applyTheme(this.scene, ThemeController.getCurrentTheme());
    parentBox.getStyleClass().add("start-scene");
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
    return parentBox;
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
    startButton.setId("Start Game");
    startButton.setOnAction(
        event -> switcher.switchToScene(new MainScene(width, height, commandController)));
    startButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    startButton.getStyleClass().add(BUTTON_STYLE);
    return startButton;
  }

  /**
   * Create a "Load Session" button.
   *
   * @param width  width of the button
   * @param height height of the button
   * @return the created button
   */
  private Button createLoadButton(int width, int height) {
    Button loadButton = new Button("Load Session");
    loadButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    loadButton.getStyleClass().add(BUTTON_STYLE);

    // Create LoadButtonController instance
    LoadButtonController loadButtonController = new LoadButtonController(commandController,
        switcher);

    // Set action for the load button
    loadButton.setOnAction(loadButtonController);

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
    languageButton.getStyleClass().add(BUTTON_STYLE);
    return languageButton;
  }

  /**
   * Create a "Select Color Scheme" button.
   *
   * @return the created button
   */
  private ComboBox<String> createColorSchemeButton() {
    ComboBox<String> themeSelector = new ComboBox<>();
    themeSelector.getItems()
        .addAll("Default Theme", "Dark Theme", "Light Theme", "Duke Theme", "UNC Theme");
    themeSelector.setValue("Default Theme"); // Default theme
    themeSelector.setOnAction(event -> {
      String newTheme = themeSelector.getValue();
      ThemeController.applyTheme(scene, newTheme);
      themeSelector.setValue(newTheme);
    });
    themeSelector.getStyleClass().add("theme-selector");
    return themeSelector;
  }


}
