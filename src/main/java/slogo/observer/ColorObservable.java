package slogo.observer;

public class BackgroundObservable extends AbstractObservable {

  private String color;

  public BackgroundObservable(String initialColor) {
    this.color = initialColor;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
    notifyObservers();
  }
}
