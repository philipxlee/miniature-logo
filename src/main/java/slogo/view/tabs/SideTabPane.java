package slogo.view.tabs;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import slogo.controller.command.CommandController;
import slogo.model.api.data.CommandHistoryModel;
import slogo.model.api.data.VariablesModel;
import slogo.observer.Observable;
import slogo.observer.Observer;
import slogo.controller.config.LanguageController;

/**
 * SideTabPane represents the view of the side tab options.
 */
public class SideTabPane extends TabPane implements Observer {

  private Map<String, TabContent> tabMap;
  private final CommandController commandController;

  /**
   * SideTabPane constructor.
   */
  public SideTabPane(CommandController commandController) {
    super();
    this.commandController = commandController;
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
    Tab commandHistoryTab = initTab(LanguageController.getText("CommandHistory"), new CommandHistoryTab(commandController));
    Tab helpDocTab = initTab(LanguageController.getText("HelpDocs"), new HelpDocTab());
    Tab userVariablesTab = initTab(LanguageController.getText("UserVariables"), new UserVariablesTab());
    Tab userCommandsTab = initTab(LanguageController.getText("UserCommands"), new UserCommandsTab());

    this.getTabs().addAll(commandHistoryTab, helpDocTab, userVariablesTab, userCommandsTab);
  }

  /**
   * Construct Tab given tab properties.
   *
   * @param title   is the title of the tab
   * @param content is the TabContent object of the tab
   */
  private Tab initTab(String title, TabContent content) {
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
    if (observable instanceof VariablesModel variablesModel) {
      TabContent tabContent = tabMap.get("User Variables");
      if (tabContent instanceof UserVariablesTab userVariablesTab) {
        userVariablesTab.updateContent(variablesModel.getAllVariables());
      }
    }
  }
}

