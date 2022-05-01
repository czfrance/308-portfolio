package oogasalad.engine.view.ControlPanel;

import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.driver.BoardHistoryException;
import oogasalad.engine.view.ApplicationAlert;
import oogasalad.engine.view.ControlPanel.ControlButton.HomeButton;
import oogasalad.engine.view.ControlPanel.ControlButton.PauseButton;
import oogasalad.engine.view.ControlPanel.ControlButton.RestartButton;
import oogasalad.engine.view.ControlPanel.ControlButton.SaveButton;
import oogasalad.engine.view.ControlPanel.ControlButton.UndoButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The purpose of this class is to hold and control all game-related controls, including restart,
 * undo, pause, save, home. It inherits from ControlPanel
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
 * @see ControlPanel, SettingsControlPanel
 */
public class GameControlPanel extends ControlPanel {

  private static final Logger LOG = LogManager.getLogger(GameControlPanel.class);

  private Controller myController;
  private Button home;
  private Button restart;
  private Button undo;
  private Button pause;
  private Button save;
  private Runnable setPauseButton;
  private Consumer<Scene> setHomeButton;
  private Runnable setSaveButton;
  private Supplier<Scene> getMyScene;

  /**
   *
   * creates a control panel that handles game-related requests
   *
   * @param controller the game controller
   * @param language user-specified language in which the UI is displayed in
   * @param setPause Runnable that defines how the pause button behaves when clicked
   * @param setHome Consumer that defines how the home button behaves when clicked
   * @param setSave Runnable that defines how the save button behaves when clicked
   * @param getScene Supplier that obtains the scene in which the home button is clicked
   */
  public GameControlPanel(Controller controller, String language, Runnable setPause,
      Consumer<Scene> setHome, Runnable setSave, Supplier<Scene> getScene) {
    super(language);
    myController = controller;
    setPauseButton = setPause;
    setHomeButton  = setHome;
    setSaveButton = setSave;
    getMyScene = getScene;
    createButtons(language);
  }


  //creates all the buttons in the given panel and adds to the root
  @Override
  protected void createButtons(String language) {
    home = new HomeButton(language, setHomeButton, getMyScene);
    restart = new RestartButton(language, this::restartGame);
    undo = new UndoButton(language, this::undoMove);
    pause = new PauseButton(language, setPauseButton);
    save = new SaveButton(language, setSaveButton);
    root.getChildren().addAll(home, restart, undo, pause, save);

  }

  //method to undo a move in the game
  private void undoMove() {
    try {
      myController.undoGameOnce();
    } catch (BoardHistoryException ex) {
      LOG.error(ex);
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Notif"), ex.getMessage());
    }
  }

  //method to restart the game from the starting configuration
  private void restartGame() {
    myController.resetGame();
  }
}
