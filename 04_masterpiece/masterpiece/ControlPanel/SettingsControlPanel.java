package oogasalad.engine.view.ControlPanel;

import javafx.scene.control.Button;
import oogasalad.engine.view.ControlPanel.ControlButton.InfoButton;
import oogasalad.engine.view.ControlPanel.ControlButton.SettingsButton;

/**
 * The purpose of this class is to hold and control all application-related controls, including
 * information and theme/sound setting selection. It inherits from ControlPanel
 *
 * Note: Please see full explanation for good design in ControlPanel.java
 *
 * This class demonstrates the "open to extension" part of the Single Responsibility Principle. As
 * can be seen, the abstract ControlPanel class has been extended so that more specific panels can
 * be made.
 *
 * GIT Commits:
 * - All commits authored by Cynthia France that have "controlpanel" or "control panel" in it:
 *    - refactored Setings Control Panel, refactored GameControlPanel, refactored ControlPanel
 *    - make ControlButton super class with subclasses for all Control Panel Buttons
 *    - ControlPanel parent class, ControlPanel package,
 *    - game control panel (home, restart, undo, pause)
 *    - get rid of control panel magic values
 *
 * @author Cynthia France
 * @see ControlPanel, GameControlPanel
 */
public class SettingsControlPanel extends ControlPanel {
  private Button info;
  private Button settings;
  private Runnable setInfoButton;
  private Runnable setSettingsButton;

  /**
   *
   * creates a control panel that handles applciation-related requests
   *
   * @param language user-specified language in which the UI is displayed in
   * @param setInfo Runnable that defines how the info button behaves when clicked
   * @param setSettings Runnable that defines how the settings button behaves when clicked
   */
  public SettingsControlPanel(String language, Runnable setInfo, Runnable setSettings) {
    super(language);
    setInfoButton = setInfo;
    setSettingsButton = setSettings;
    createButtons(language);
  }

  @Override
  protected void createButtons(String language) {
    info = new InfoButton(language, setInfoButton);
    settings = new SettingsButton(language, setSettingsButton);
    root.getChildren().addAll(info, settings);
  }

}
