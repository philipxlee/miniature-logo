package slogo.controller;


import slogo.model.TurtleModel;
import slogo.view.TurtleView;

public class MainController {

  public static void main(String[] args) {
    System.out.println("MainController");
    TurtleModel turtle = new TurtleModel();
    TurtleView turtleView = new TurtleView();
    turtle.addObserver(turtleView);
    turtle.moveForward(100);
  }
}
