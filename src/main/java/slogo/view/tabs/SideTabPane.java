package slogo.view.tabs;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import slogo.model.api.CommandHistoryModel;
import slogo.observer.Observable;
import slogo.observer.Observer;

public class SideTabPane extends TabPane implements Observer {

  public SideTabPane() {
    super();
    initializeTabs();
  }

  /**
   * Initialize the tabs in the TabPane
   */
  private void initializeTabs() {
    this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // Disable tab closing

    // Create tabs for different views
    Tab commandHistoryTab = createTab("Command History", new CommandHistoryTab());
    Tab helpDocTab = createTab("Help Docs", new HelpDocTab());
    Tab userVariablesTab = createTab("User Variables", new UserVariablesTab());
    Tab userCommandsTab = createTab("User Commands", new UserCommandsTab());

    // Add tabs to the TabPane
    this.getTabs().addAll(commandHistoryTab, helpDocTab, userVariablesTab, userCommandsTab);
  }

  /**
   * Create a Tab with the specified title and content
   *
   * @param title   the title of the tab
   * @param content the content of the tab
   * @return the created Tab
   */
  private Tab createTab(String title, TabContent content) {
    Tab tab = new Tab(title);
    tab.setContent(content.getContent());
    return tab;
  }

  @Override
  public void update(Observable observable) {
    if (observable instanceof CommandHistoryModel) {
      System.out.println("Side Tab Pane View: Updated command!");
    }
  }
}

