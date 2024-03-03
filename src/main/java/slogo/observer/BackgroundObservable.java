package slogo.observer;

public class BackgroundObservable extends AbstractObservable {

  private String color;

  /**
   * Constructs a new BackgroundObservable.
   *
   * @param initialColor the initial color of the observable
   */
  public BackgroundObservable(String initialColor) {
    super();
    this.color = initialColor;
  }

  /**
   * Returns the color of the observable.
   *
   * @return the color of the observable
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets the color of the observable.
   *
   * @param color the new color of the observable
   */
  public void setColor(String color) {
    this.color = color;
    notifyObservers();
  }
}
