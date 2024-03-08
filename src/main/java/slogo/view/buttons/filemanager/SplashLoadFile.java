package slogo.view.buttons.filemanager;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import slogo.controller.command.CommandController;
import slogo.controller.display.SceneSwitcher;
import slogo.view.scenes.main.MainScene;

public class SplashLoadFile extends AbstractFileProcessor implements FileLoader {

  private static int WIDTH = 1000;
  private static int HEIGHT = 700;
  private final CommandController commandController;
  private final SceneSwitcher switcher;

  public SplashLoadFile(CommandController commandController, SceneSwitcher switcher) {
    this.commandController = commandController;
    this.switcher = switcher;
  }

  @Override
  public void handle(ActionEvent event) {
    Optional<File> selectedFile = Optional.ofNullable(
        createFileChooser("Open File", "*.slogo").showOpenDialog(null));
    selectedFile.ifPresent(this::loadFile);
  }

  @Override
  public void loadFile(File file) {
    try {
      String commands = readFileContents(file);
      switchToMainScene(commands);
    } catch (IOException e) {
      throw new RuntimeException("File couldn't be loaded: " + e.getMessage(), e);
    }
  }

  private void switchToMainScene(String commands) {
    MainScene mainScene = new MainScene(WIDTH, HEIGHT, commandController, commands);
    switcher.switchToScene(mainScene);
  }
}
