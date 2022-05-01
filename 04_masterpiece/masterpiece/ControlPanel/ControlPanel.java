package oogasalad.engine.view.ControlPanel;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Abstract class for control panels that control game and application function
 *
 * The purpose of this class is to serve as a parent ControlPanel class, which control game and application function.
 * Children of ControlPanel will contain more specific ways control the game.
 *
 * I think this is well designed as it abstracts ControlPanel into chunks that make sense for
 * the purpose of the project. By doing this, the Single Responsibility, Open-Closed, and
 * Dependency Inversion Principles are all met.
 *
 * The sole purpose of this class is to oversee the operation of game controls. This not only makes the purpose
 * of this class clearer, but also cleans up code nicely in GameView, which is where this class
 * is being used. ControlPanel is a parent class that is closed to modification, but can be extended a number of
 * different ways, as I've demonstrated with the child classes. In this hierarchy, lower level
 * modules do not depend on higher level ones (only higher -> lower).
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
 * @see GameControlPanel, SettingsControlPanel
 */
public abstract class ControlPanel {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/resource-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static String IMAGES_FOLDER = "/engine-view/images/";

  protected VBox root;
  protected ResourceBundle myResources;

  /**
   * creates a panel of buttons with which the user can control game and application settings
   *
   * @param language user-specified language in which the UI is displayed in
   */
  public ControlPanel(String language) {
    myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
    root = new VBox();
    root.setId("cp-root");
  }

  /**
   *
   * returns the root of this panel
   *
   * @return root, a VBox consisting of all panel Buttons
   */
  public Node getRoot() {
    return root;
  }

  //creates all the buttons in a given panel
  protected abstract void createButtons(String language);
}
