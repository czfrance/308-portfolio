package oogasalad.engine.view.ControlPanel.ControlButton;

/**
 * The purpose of this class is to create and define the characteristics of the info button, which
 * presents information about the game. It inherits from ControlButton
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
 * @see ControlButton, HomeButton, PauseButton, SaveButton, RestartButton, SettingsButton, UndoButton
 */
public class InfoButton extends ControlButton {

  /**
   *
   * creates and defines the characteristics of the save button, presents information about the game.
   *
   * @param language user-specified language in which the UI is displayed in
   * @param setInfo Runnable that defines how the info button behaves when clicked
   */
  public InfoButton(String language, Runnable setInfo) {
    super(language);
    imagePath = IMAGES_FOLDER + imBundle.getString("Info");
    createButton();
    setAction(setInfo);
  }
}
