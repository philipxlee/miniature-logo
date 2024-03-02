package slogo.observer;

public class ColorObservable extends AbstractObservable {

  private String color;

  public ColorObservable(String initialColor) {
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
