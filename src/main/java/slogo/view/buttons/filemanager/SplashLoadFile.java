package slogo.view.buttons.filemanager;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import slogo.controller.command.CommandController;
import slogo.controller.display.SceneSwitcher;
import slogo.view.scenes.main.MainScene;

public class SplashLoadFile extends AbstractFileLoader {

  private final CommandController commandController;
  private final SceneSwitcher switcher;

  public SplashLoadFile(CommandController commandController, SceneSwitcher switcher) {
    this.commandController = commandController;
    this.switcher = switcher;
  }

  @Override
  public void handle(ActionEvent event) {
    FileChooser fileChooser = createFileChooser();
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      loadFile(selectedFile);
    }
  }

  @Override
  public void loadFile(File file) {
    try {
      String commands = readFileContents(file);
      switcher.switchToScene(new MainScene(1000, 700, commandController, commands));
    } catch (IOException e) {
      throw new RuntimeException("File couldn't be loaded", e);
    }
  }

  private FileChooser createFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open SLogo File");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SLogo Files", "*.slogo"));
    return fileChooser;
  }
}
