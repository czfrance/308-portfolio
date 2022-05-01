package oogasalad.engine.view.ControlPanel.ControlButton;

/**
 * The purpose of this class is to create and define the characteristics of the restart button, which
 * resets the game back to its starting configuration. It inherits from ControlButton
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
 * @see ControlButton, HomeButton, InfoButton, SaveButton, PauseButton, SettingsButton, UndoButton
 */
public class RestartButton extends ControlButton {

  /**
   *
   * creates and defines the characteristics of the restart button, resets the game back to its starting configuration.
   *
   * @param language user-specified language in which the UI is displayed in
   * @param setRestart Runnable that defines how the restart button behaves when clicked
   */
  public RestartButton(String language, Runnable setRestart) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Restart");
    createButton();
    setAction(setRestart);
  }
}
