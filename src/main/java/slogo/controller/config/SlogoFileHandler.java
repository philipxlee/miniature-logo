package slogo.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SlogoFileHandler {

  /**
   * Save the given commands to a .slogo file.
   *
   * @param commands List of commands to save.
   * @param filePath Path to save the file.
   */
  public static void saveCommandsToFile(List<String> commands, String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      for (String command : commands) {
        writer.write(command);
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

