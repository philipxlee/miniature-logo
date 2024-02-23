package slogo.view;


import slogo.view.GeneralScene;

public interface SceneSwitcher {

  /**
   * Switch to new AbstractScene
   *
   * @param scene: new AbstractScene to switch to
   */
  void switchToScene(GeneralScene scene);

}
