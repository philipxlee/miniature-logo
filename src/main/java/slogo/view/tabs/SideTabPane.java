package slogo.view.tabs;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import slogo.model.api.data.CommandHistoryModel;
import slogo.observer.Observable;
import slogo.observer.Observer;

/**
 * SideTabPane represents the view of the side tab options.
 */
public class SideTabPane extends TabPane implements Observer {

  private Map<String, TabContent> tabMap;

  /**
   * SideTabPane constructor.
   */
  public SideTabPane() {
    super();
    initializeTabs();
  }

  /**
   * Initialize the tabs in the TabPane.
   */
  private void initializeTabs() {
    this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // Disable tab closing
    this.tabMap = new HashMap<>();
    constructTabs();
  }

  /**
   * Construct required tabs.
   */
  private void constructTabs() {
    Tab commandHistoryTab = createTab("Command History", new CommandHistoryTab());
    Tab helpDocTab = createTab("Help Docs", new HelpDocTab());
    Tab userVariablesTab = createTab("User Variables", new UserVariablesTab());
    Tab userCommandsTab = createTab("User Commands", new UserCommandsTab());

    this.getTabs().addAll(commandHistoryTab, helpDocTab, userVariablesTab, userCommandsTab);
  }

  /**
   * Construct Tab given tab properties.
   *
   * @param title   is the title of the tab
   * @param content is the TabContent object of the tab
   */
  private Tab createTab(String title, TabContent content) {
    Tab tab = new Tab(title);
    tab.setContent(content.getContent());
    tabMap.put(title, content);
    return tab;
  }

  /**
   * Update pane when observed models are updated.
   *
   * @param observable the observable model triggering the update
   */
  @Override
  public void update(Observable observable) {
    if (observable instanceof CommandHistoryModel commandHistoryModel) {
      TabContent tabContent = tabMap.get("Command History");
      if (tabContent instanceof CommandHistoryTab commandHistoryContent) {
        commandHistoryContent.updateContent(commandHistoryModel.iterator());
      }
    }
  }
}

