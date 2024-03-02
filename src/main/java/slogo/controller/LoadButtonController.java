package slogo.controller;


import javafx.stage.FileChooser;
import slogo.exceptions.InvalidCommandException;
import slogo.view.scenes.main.MainScene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * LoadButton is a button for loading a session from a file.
 */
public class LoadButtonController {

  private final CommandController commandController;
  private final SceneSwitcher switcher;

  /**
   * Constructor for LoadButtonController.
   *
   * @param commandController the CommandController
   * @param switcher          the SceneSwitcher
   */
  public LoadButtonController(CommandController commandController, SceneSwitcher switcher) {
    this.commandController = commandController;
    this.switcher = switcher;
  }

  /**
   * Method to handle the "Load Session" button action.
   */
  public void handleLoad() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open SLogo File");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("SLogo Files", "*.slogo")
    );

    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      try {
        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
        StringBuilder commandsBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
          commandsBuilder.append(line).append("\n");
        }
        reader.close();
        String commands = commandsBuilder.toString().trim();
        commandController.executeCommand(commands);
        switcher.switchToScene(new MainScene(800, 600, commandController));
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InvalidCommandException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

