package slogo.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class LoadButtonController implements EventHandler<ActionEvent> {

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
  @Override
  public void handle(ActionEvent event) {
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
        System.out.println(commands);
        switcher.switchToScene(new MainScene(1000, 700, commandController, commands));
      } catch (IOException e) {
        throw new RuntimeException("File couldn't be loaded");
      }
    }
  }
}

