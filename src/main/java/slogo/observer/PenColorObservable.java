package slogo.observer;

public class PenColorObservable extends AbstractObservable {

  private String color;

  public PenColorObservable(String initialColor) {
    super();
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
