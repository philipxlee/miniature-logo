package slogo.controller;

public class MainController {

  public static void main(String[] args) {
    CommandController controller = new CommandController();

    // Temporary test code: view would be calling this
    controller.executeCommand("bk 50");
    controller.executeCommand("rt 90");
    controller.executeCommand("fd 10");
    controller.executeCommand("seth 50");

  }

}
