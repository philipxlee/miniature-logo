package slogo.controller;

import static javafx.application.Application.launch;

import javafx.application.Application;

public class MainController {

  /**
   * Main method to run the program
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    CommandController controller = new CommandController();
    launch(ViewController.class);

    // Temporary test code: view would be calling this
    controller.executeCommand("bk 50");
    controller.executeCommand("rt 90");
    controller.executeCommand("fd 10");
    controller.executeCommand("seth 50");

  }

}
