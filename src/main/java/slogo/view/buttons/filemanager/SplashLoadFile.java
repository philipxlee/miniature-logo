package slogo.view.buttons.filemanager;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import slogo.controller.command.CommandController;
import slogo.controller.display.SceneSwitcher;
import slogo.view.alert.Alert;
import slogo.view.scenes.main.MainScene;

/**
 * SplashLoadFile handles functionality for loading file.
 */
public class SplashLoadFile extends AbstractFileProcessor implements FileLoader {

  private static int WIDTH = 1000;
  private static int HEIGHT = 700;
  private final CommandController commandController;
  private final SceneSwitcher switcher;

  /**
   * The SplashLoadFile constructor creates a new instance of SplashLoadFile.
   *
   * @param commandController The command controller
   * @param switcher The scene switcher
   */
  public SplashLoadFile(CommandController commandController, SceneSwitcher switcher) {
    super();
    this.commandController = commandController;
    this.switcher = switcher;
  }

  /**
   * Handles the event of loading a file.
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    Optional<File> selectedFile = Optional.ofNullable(
        createFileChooser("Open File", "*.slogo").showOpenDialog(null));
    selectedFile.ifPresent(this::loadFile);
  }

  /**
   * Loads the file.
   *
   * @param file the file to be loaded
   */
  @Override
  public void loadFile(File file) {
    try {
      String commands = readFileContents(file);
      String cleanedCommands = Arrays.stream(commands.split("\\n"))
          .filter(line -> !line.trim().isEmpty() && !line.trim().startsWith("#"))
          .collect(Collectors.joining("\n"));
      switchToMainScene(cleanedCommands);
    } catch (IOException e) {
      Platform.runLater(
          () -> Alert.showError("Load Error", "File couldn't be loaded: " + e.getMessage()));
    }
  }


  private void switchToMainScene(String commands) {
    MainScene mainScene = new MainScene(WIDTH, HEIGHT, commandController, commands);
    switcher.switchToScene(mainScene);
  }
}
