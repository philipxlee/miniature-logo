package slogo.controller.display;

import slogo.view.scenes.Scene;

/**
 * SceneSwitcher abstraction defines module that cna switch scenes (ViewController).
 */
public interface SceneSwitcher {

  /**
   * Switch to new Scene.
   *
   * @param scene new Scene to switch to
   */
  void switchToScene(Scene scene);
}
