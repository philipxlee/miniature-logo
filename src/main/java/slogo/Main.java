package slogo;

import slogo.controller.MainController;

public class Main {
  public static void main(String[] args) {
    MainController controller = new MainController();
    controller.startProgram();

    // Temporary test code: view would be calling this
    controller.executeCommand("fd 50");
    controller.executeCommand("rt 90");
  }
}
