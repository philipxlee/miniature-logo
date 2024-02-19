package slogo.controller;


import slogo.model.api.TurtleModel;
import slogo.view.TurtleView;

public class MainController {

  public static void main(String[] args) {
    TurtleModel turtle = new TurtleModel();
    TurtleView turtleView = new TurtleView();
    turtle.addObserver(turtleView);
    turtle.moveForward(100);
    System.out.println("MainController: Updated TurtleModel");
  }
}
