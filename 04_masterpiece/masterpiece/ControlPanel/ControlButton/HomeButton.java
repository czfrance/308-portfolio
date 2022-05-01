package oogasalad.engine.view.ControlPanel.ControlButton;

import java.util.function.Consumer;
import java.util.function.Supplier;
import javafx.scene.Scene;

/**
 * The purpose of this class is to create and define the characteristics of the home button, which
 * closes the current stage and takes the user back to the home engine screen. It inherits from ControlButton
 *
 * Note: Please see full explanation for good design in ControlButton.java
 *
 * This class demonstrates the "open to extension" part of the Single Responsibility Principle. As
 * can be seen, the abstract ControlPanel class has been extended so that more specific panels can
 * be made.
 *
 * GIT Commits:
 * - All commits authored by Cynthia France that have "controlbutton" or "control button" in it:
 *    - make ControlButton super class with subclasses for all Control Panel Buttons
 *
 * @author Cynthia France
 * @see ControlButton, PauseButton, InfoButton, SaveButton, RestartButton, SettingsButton, UndoButton
 */
public class HomeButton extends ControlButton {

  /**
   *
   * creates and defines the characteristics of the undo button, closes the current stage and takes the user back to the home engine screen.
   *
   * @param language user-specified language in which the UI is displayed in
   * @param setHome Consumer that defines how the home button behaves when clicked
   * @param getScene Supplier that obtains the scene in which the home button is clicked
   */
  public HomeButton(String language, Consumer<Scene> setHome, Supplier<Scene> getScene) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Home");
    createButton();
    setAction(setHome, getScene);
  }

  //sets the behavior of the home button
  protected void setAction(Consumer<Scene> setButton, Supplier<Scene> getScene) {
    this.setOnAction(e -> setButton.accept(getScene.get()));
  }
}
